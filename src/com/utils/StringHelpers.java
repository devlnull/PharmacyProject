package com.utils;

public class StringHelpers {
    public static boolean IsNullOrEmpty(String str){
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
}
