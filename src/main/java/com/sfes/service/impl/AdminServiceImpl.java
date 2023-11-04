package com.sfes.service.impl;

import com.sfes.mapper.AdminMapper;
import com.sfes.pojo.Admin;
import com.sfes.pojo.AdminExample;
import com.sfes.service.AdminService;
import com.sfes.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : sfes
 * @date : 2023/10/28
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
        AdminExample adminExample=new AdminExample();
        adminExample.createCriteria().andANameEqualTo(name);
        //获取加密后的密码调用mapper层去数据库查询
        String md5Pwd = MD5Util.getMD5(pwd);
        Admin admin = adminMapper.selectByExample(adminExample).get(0);
        if(!admin.getaPass().equals(md5Pwd)){
            return null;
        }
        return admin;
    }
}
