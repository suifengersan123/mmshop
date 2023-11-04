package com.sfes.service;

import com.sfes.pojo.Admin;
import org.springframework.stereotype.Service;

/**
 * @author : sfes
 * @date : 2023/10/28
 */

public interface AdminService {
    //完成登录判断
    Admin login(String name,String pwd);
}
