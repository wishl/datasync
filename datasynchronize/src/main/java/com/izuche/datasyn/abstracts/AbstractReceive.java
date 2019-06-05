package com.izuche.datasyn.abstracts;

import com.izuche.datasyn.common.NameUtils;
import com.izuche.datasyn.properties.DataSynProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public abstract class AbstractReceive extends AbstractDataSyn {

    private static final Logger logger = LoggerFactory.getLogger(AbstractReceive.class);
    private String deleteMethod;
    private String sendMethod;
    private Class<?> sendClass;

    public AbstractReceive(DataSynProperties dataSynProperties) {
        super(dataSynProperties);
        this.deleteMethod = dataSynProperties.getDeleteMethod();
        this.sendMethod = dataSynProperties.getSendMethod();
        this.sendClass = dataSynProperties.getSendClass();
    }

    public void listener() {
        CuratorFramework curator = getCurator();
        // 监听namespace的所有子节点
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curator,"/",true);
        try {
            pathChildrenCache.getListenable().addListener((CuratorFramework client1, PathChildrenCacheEvent event)->{
                String key;
                switch (event.getType()){
                    case CHILD_ADDED:
                        logger.info("CHILD_ADDEDPATH:{} ", event.getData().getPath());
                        logger.info("CHID_ADDED:{}",event.getData());
                        key = event.getData().getPath();
                        send(key,get(key));
                        break;
                    case CHILD_REMOVED:
                        logger.info("CHILD_REMOVEDPATH:{}",event.getData().getPath());
                        logger.info("CHILD_REMOVED:{}",event.getData());
                        key = event.getData().getPath();
                        delete(key);
                        break;
                    case CHILD_UPDATED:
                        logger.info("CHILD_UPDATED:{}",event.getData().getPath());
                        logger.info("CHILD_UPDATEDPATH:{}",event.getData());
                        key = event.getData().getPath();
                        send(key,get(key));
                        break;
                    default:
                        System.out.println("other");
                        break;
                }
            });
            pathChildrenCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDeleteMethod() {
        return deleteMethod;
    }

    public void setDeleteMethod(String deleteMethod) {
        this.deleteMethod = deleteMethod;
    }

    public String getSendMethod() {
        return sendMethod;
    }

    public void setSendMethod(String sendMethod) {
        this.sendMethod = sendMethod;
    }

    public Class<?> getSendClass() {
        return sendClass;
    }

    public void setSendClass(Class<?> sendClass) {
        this.sendClass = sendClass;
    }
}
