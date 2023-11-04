package com.sfes.utils;

import java.util.UUID;

/**
 * @author : sfes
 * @date : 2023/11/2
 */
public class FileNameUtil {

    //根据uuid生成文件名
    public static String getUUIDFileName(){
        UUID uuid=UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    //从请求头中提取文件名和类型
    public static String getRealFileName(String context){
        int index=context.lastIndexOf("=");
        String fileName=context.substring(index+2,context.length()-1);
        return fileName;
    }

    //根据给定的文件名和后缀截取文件类型
    public static String getFileType(String fileName){
        int index=fileName.lastIndexOf(".");
        return fileName.substring(index);
    }
}
