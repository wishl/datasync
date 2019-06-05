package com.izuche.datasyn.abstracts;

import com.izuche.datasyn.common.ByteToObject;
import com.izuche.datasyn.inertfaces.DataSyn;
import com.izuche.datasyn.properties.DataSynProperties;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstarctSend extends AbstractDataSyn {

    private static final Logger logger = LoggerFactory.getLogger(AbstarctSend.class);

    public AbstarctSend(DataSynProperties dataSynProperties) {
        super(dataSynProperties);
    }

    public void set(String key, Object value) {
        Assert.hasText(key,"key 不能为空");
        Assert.notNull(value,"value 不能为空");
        try {
            CuratorFramework curator = getCurator();
            if(curator.checkExists().forPath(key) == null){
                curator.create().creatingParentsIfNeeded().forPath(key);
            }
            byte[] bytes;
            if(value instanceof String){
                bytes = ((String) value).getBytes();
            }else{
                bytes = ByteToObject.Object2Byte(value);
            }
            curator.setData().forPath(key,bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(String key) {
        Assert.hasText(key,"key 不能为空");
        try {
            CuratorFramework curator = getCurator();
            curator.delete().forPath(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
