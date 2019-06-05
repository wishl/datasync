package com.izuche.datasyn.abstracts;

import com.izuche.datasyn.common.ByteToObject;
import com.izuche.datasyn.inertfaces.DataSyn;
import com.izuche.datasyn.properties.DataSynProperties;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.util.Assert;

import java.io.Serializable;

public abstract class AbstractDataSyn implements DataSyn {

    private CuratorFramework curator;
    private String namespace;
    private String connectString;
    private int sessionTimeOut;
    private int connectTimeOut;
    private int baseSleepTimeMs;
    private int maxRetries;
    private Class retryClass;

    public AbstractDataSyn(DataSynProperties dataSynProperties){
        try {
            this.namespace = dataSynProperties.getNamespace();
            this.connectString = dataSynProperties.getConnectString();
            this.sessionTimeOut = dataSynProperties.getSessionTimeOut();
            this.connectTimeOut = dataSynProperties.getConnectTimeOut();
            this.baseSleepTimeMs = dataSynProperties.getBaseSleepTimeMs();
            this.maxRetries = dataSynProperties.getMaxRetries();
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs,maxRetries);
            CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().namespace(namespace).connectString(connectString).sessionTimeoutMs(sessionTimeOut)
                    .connectionTimeoutMs(connectTimeOut).retryPolicy(retryPolicy);
            this.curator = builder.build();
            curator.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Serializable get(String key, Class<?> require) {
        Assert.hasText(key,"key 不能为空");
        CuratorFramework curator = getCurator();
        try {
            byte[] bytes = curator.getData().forPath(key);
            Object object;
            if(require!=null){
                object = ByteToObject.Byte2Object(bytes, require);
            }else{
                object = new String(bytes,"utf-8");
            }
            return (Serializable) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Serializable get(String key){
        return get(key,null);
    }

    @Override
    public void set(String key, Object value) {
    }

    public CuratorFramework getCurator() {
        return curator;
    }

}
