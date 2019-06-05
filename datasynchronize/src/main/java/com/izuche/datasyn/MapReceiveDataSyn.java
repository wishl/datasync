package com.izuche.datasyn;

import com.izuche.datasyn.abstracts.AbstractReceive;
import com.izuche.datasyn.common.NameUtils;
import com.izuche.datasyn.entity.Data;
import com.izuche.datasyn.properties.DataSynProperties;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;


public class MapReceiveDataSyn extends AbstractReceive {

    private static final Logger logger = LoggerFactory.getLogger(ZooKeeper.class);

    private DataSynProperties dataSynProperties;

    public Data<String,Object> map = new Data<>();

    public MapReceiveDataSyn(DataSynProperties dataSynProperties) {
        super(dataSynProperties);
        this.dataSynProperties = dataSynProperties;
        try {
            listener();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("初始化数据失败");
        }
    }

    public ConcurrentHashMap<String, Object> getMap() {
        return map;
    }

    // 获取具体value
    public Object getByKey(String key){
        return getMap().get(key);
    }

    @Override
    public void send(String key, Object value) {
        key = NameUtils.getKey(key);
        map.put(key,value);
    }

    @Override
    public void delete(String key) {
        key = NameUtils.getKey(key);
        map.remove(key);
    }


}
