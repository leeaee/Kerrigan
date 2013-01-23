package com.modoop.zerg.taipan.core.util;

import java.io.FileInputStream;

/**
 * @author: Genkyo Lee
 * @date: 11/9/12
 * <p/>
 * Java 6: Version 50.0
 * Java 5: Version 49.0
 * Java 1.4.2: Version 48.0
 */
public class VersionUtils
{
    private static final String str = "D:/MMSCFetchIfGetOriginatorProfile.class";

    public static void main(String args[])
    {
        try
        {
            // 读取文件数据,文件是当前目录下的First.class
            FileInputStream fis = new FileInputStream(str);
            int length = fis.available();
            // 文件数据
            byte[] data = new byte[length];
            // 读取文件到字节数组
            fis.read(data);
            // 关闭文件
            fis.close();
            // 解析文件数据
            parseFile(data);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private static void parseFile(byte[] data)
    {
        // 输出魔数
        System.out.print("magic:0x");
        System.out.print(Integer.toHexString(data[0]).substring(6).toUpperCase());
        System.out.print(Integer.toHexString(data[1]).substring(6).toUpperCase());
        System.out.print(Integer.toHexString(data[2]).substring(6).toUpperCase());
        System.out.println(Integer.toHexString(data[3]).substring(6).toUpperCase());
        // 主版本号和次版本号码
        int minor_version = (((int) data[4]) << 8) + data[5];
        int major_version = (((int) data[6]) << 8) + data[7];
        System.out.println("version:" + major_version + "." + minor_version);
    }
}
