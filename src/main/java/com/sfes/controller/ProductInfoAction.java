package com.sfes.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sfes.pojo.ProductInfo;
import com.sfes.pojo.vo.ProductInfoVo;
import com.sfes.service.ProductInfoService;
import com.sfes.utils.FileNameUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.github.pagehelper.parser.SqlServerParser.PAGE_SIZE;

/**
 * @author : sfes
 * @date : 2023/10/28
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    public static final int PAGE_SIZE=5;
    private String saveFileName="";
    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }

    //显示第一页的5条商品
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        PageInfo pageInfo=null;
        Object vo = request.getSession().getAttribute("prodVo");
        if(vo!=null){
            pageInfo=productInfoService.splitPageVo((ProductInfoVo)vo, PAGE_SIZE);
        }else {
            //得到第一页数据
            pageInfo=productInfoService.splitPage(1, PAGE_SIZE);
        }
        request.setAttribute("pb", pageInfo);

        return "product";
    }

    //ajax分页翻页处理
    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxSplit(ProductInfoVo productInfoVo, HttpSession session){
        //取得当前page参数页面的数据
        PageInfo info=productInfoService.splitPageVo(productInfoVo, PAGE_SIZE);
        session.setAttribute("pb", info);
    }

    //异步ajax文件上传处理
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        //UUID+文件类型
        saveFileName= FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        String path = request.getServletContext().getRealPath("/image_big");
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject object=new JSONObject();
        object.put("imgurl", saveFileName);

        return object.toString();
    }

    @RequestMapping("/save")
    public String save(ProductInfo info,HttpServletRequest request){
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int num=-1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(num>0){
            request.setAttribute("msg", "增加成功");
        }else {
            request.setAttribute("msg", "增加失败");
        }
        saveFileName="";
        return "forward:/prod/split.action";
    }

    @RequestMapping("/one")
    public String one(int pid,ProductInfoVo productInfoVo,HttpSession session, Model model){
        ProductInfo info = productInfoService.getById(pid);
        model.addAttribute("prod", info);
        //将多条件及页码放入session 更新结束分页时读取条件和页码
        session.setAttribute("prodVo", productInfoVo);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){
        //因为ajax图片异步上传，如果saveFileName里有名称，就说明上传过。
        if(!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }
        int num=-1;

        try {
            num = productInfoService.update(info);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(num==-1){
            request.setAttribute("msg", "更新失败");
        }else {
            request.setAttribute("msg", "更新成功");
        }
        saveFileName="";
        return "forward:/prod/split.action";
    }

    @RequestMapping("/delete")
    public String delete(Integer pid,ProductInfoVo productInfoVo,HttpServletRequest request){
        int num=-1;
        try {
            num = productInfoService.delete(pid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(num>0){
            request.setAttribute("msg", "删除成功");
            request.getSession().setAttribute("dvo",productInfoVo);
        }else {
            request.setAttribute("msg", "删除失败");
        }

        //删除结束跳到分页显示
        return "forward:/prod/deleteAjaxSplit.action";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit",produces = "text/html;charset=UTF-8")
    public Object deleteAjaxSplit(HttpServletRequest request){
        PageInfo pageInfo=null;
        Object dvo = request.getSession().getAttribute("dvo");
        if(dvo!=null){
            pageInfo = productInfoService.splitPageVo((ProductInfoVo)dvo, PAGE_SIZE);
        }else{
            //取得第一页数据
            pageInfo = productInfoService.splitPage(1, PAGE_SIZE);
        }


        request.getSession().setAttribute("pb",pageInfo);
        return request.getAttribute("msg");
    }

    @RequestMapping("/deleteBatch")
    public String deleteBatch(String pids,HttpServletRequest request){
        String[] ids=pids.split(",");

        try {
            int num = productInfoService.deleteBatch(ids);
            if(num>0){
                request.setAttribute("msg", "批量删除成功");
            }else {
                request.setAttribute("msg", "批量删除失败");
            }
        } catch (Exception e) {
            request.setAttribute("msg", "商品不可删除");
        }

        return "forward:/prod/deleteAjaxSplit.action";
    }

    @ResponseBody
    @RequestMapping("/condition")
    public void condition(ProductInfoVo productInfoVo,HttpSession session){
        List<ProductInfo> productInfos = productInfoService.selectCondition(productInfoVo);
        session.setAttribute("list", productInfos);

    }
}
