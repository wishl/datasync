package com.izuche.datasyn.entity;

import java.util.concurrent.ConcurrentHashMap;

public class Data<K,V> extends ConcurrentHashMap<K,V> {


    private static final String send = "send";
    private static final String delete = "delete";
    private static final String className = "com.izuche.datasyn.MapReceiveDataSyn";

    @Override
    public V put(K key, V value) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            String methodName = element.getMethodName();
            if(element.getClassName().equals(className)
                    &&(methodName.equals(send))){
                return super.put(key, value);
            }
        }
        throw new RuntimeException("不能修改");
    }

    @Override
    public V remove(Object key) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            String methodName = element.getMethodName();
            if(element.getClassName().equals(className)
                    &&(methodName.equals(delete))){
                return super.remove(key);
            }
        }
        throw new RuntimeException("不能修改");
    }

    public static void main(String[] args) {
       dealMap();
    }

    public static void dealMap(){
        Data<String,Object> data = new Data<>();
        data.put("123","123");
    }
}