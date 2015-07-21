package com.cmcc.edu.util;

import java.io.PrintStream;
import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

public class EncryptHelper
{

    public EncryptHelper()
    {
    }

    public static String toMD5(String str)
    {
        String newstr = null;
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return newstr;
    }

    public static void main(String args[])
    {
        System.out.println(toMD5("111111"));
    }
}