package com.izuche.datasyn;

import com.izuche.datasyn.abstracts.AbstarctSend;
import com.izuche.datasyn.common.NameUtils;
import com.izuche.datasyn.properties.DataSynProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SendDataSyn extends AbstarctSend {

    private static final Logger logger = LoggerFactory.getLogger(SendDataSyn.class);

    private DataSynProperties dataSynProperties;

    public SendDataSyn(DataSynProperties dataSynProperties) {
        super(dataSynProperties);
        this.dataSynProperties = dataSynProperties;
        logger.info("send 创建");
    }

    @Override
    public void send(String key, Object value) {
        key = NameUtils.getPath(key);
        set(key,value);
    }

    @Override
    public void delete(String key){
       key = NameUtils.getPath(key);
       remove(key);
    }

}
