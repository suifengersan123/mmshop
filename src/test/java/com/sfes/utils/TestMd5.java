package com.sfes.utils;

import org.junit.Test;

/**
 * @author : sfes
 * @date : 2023/10/28
 */
public class TestMd5 {

    @Test
    public void testMd5(){
        System.out.println("MD5Util.getMD5(\"admin\") = " + MD5Util.getMD5("000000"));
    }
}
