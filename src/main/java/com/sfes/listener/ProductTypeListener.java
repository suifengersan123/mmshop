package com.sfes.listener;

import com.sfes.pojo.ProductType;
import com.sfes.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author : sfes
 * @date : 2023/11/2
 */
@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductTypeService productTypeService= (ProductTypeService) applicationContext.getBean("ProductTypeServiceImpl");
        List<ProductType> typeList = productTypeService.getAll();
        //放到全局作用域中
        servletContextEvent.getServletContext().setAttribute("ptlist", typeList);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
