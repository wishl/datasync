package com.izuche.syntest.controller;

import com.izuche.syntest.service.DataTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DataController {

    @Autowired
    private DataTest dataTest;

    @GetMapping("/getMap")
    public Map<String,Object> getMap(){
        return dataTest.getMap();
    }

    @GetMapping("/sendData")
    public String send(String key,String data){
        dataTest.setSendDataSyn(key,data);
        return "success";
    }

    @GetMapping("/deleteData")
    public String delete(String key){
        dataTest.deleteSendDataSyn(key);
        return "success";
    }

}
