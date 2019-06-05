package com.izuche.syntest.service;

import com.izuche.datasyn.MapReceiveDataSyn;
import com.izuche.datasyn.SendDataSyn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DataTest {

    @Autowired
    private MapReceiveDataSyn receive;

    @Autowired
    private SendDataSyn sendDataSyn;

    Map<String,Object> map = new ConcurrentHashMap<>();

    public void delete(String key){
        map.remove(key);
    }

    public void send(String key,Object value){
        System.out.println("==="+key);
        System.out.println("==="+value);
        map.put(key,value);
    }

    public Map<String,Object> getMap(){
        return receive.map;
    }

    public void setSendDataSyn(String key,Object value){
        sendDataSyn.send(key,value);
    }

    public void deleteSendDataSyn(String key){
        sendDataSyn.delete(key);
    }

}
