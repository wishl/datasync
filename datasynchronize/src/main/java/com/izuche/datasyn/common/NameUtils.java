package com.izuche.datasyn.common;

public class NameUtils {


    public static String getKey(String path){
        if(path.startsWith("/")) {
            return path.substring(1);
        }
        return path;
    }

    public static String getPath(String key){
        if(!key.startsWith("/")) {
            return "/" + key;
        }
        return key;
    }

}
