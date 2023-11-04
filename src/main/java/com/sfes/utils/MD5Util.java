package com.sfes.utils;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author : sfes
 * @date : 2023/10/28
 */
public class MD5Util {


    public final static String getMD5(String str){
        try {
            return DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
