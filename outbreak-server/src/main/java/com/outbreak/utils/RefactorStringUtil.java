package com.outbreak.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RefactorStringUtil {
    public static String refactorString(String data,String regex){
        //表达式对象
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data);
        if(m.find()) {  //该方法扫描输入的序列，查找与该模式匹配的一个子序列
            data=m.group();
        }
        return data;
    }
}
