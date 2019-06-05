package com.izuche.datasyn.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("spring.data.sync")
public class DataSynProperties {

    private String type;
    private int sessionTimeOut;
    private Class sendClass;
    private String sendMethod;
    private String deleteMethod;
    private Boolean send;
    private Boolean receive;
    private String namespace;
    private String connectString;
    private int connectTimeOut;
    private int baseSleepTimeMs;
    private int maxRetries;
    private int threadNum;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSessionTimeOut() {
        return sessionTimeOut;
    }

    public String getSendMethod() {
        return sendMethod;
    }

    public void setSendMethod(String sendMethod) {
        this.sendMethod = sendMethod;
    }

    public Class getSendClass() {
        return sendClass;
    }

    public void setSendClass(Class sendClass) {
        this.sendClass = sendClass;
    }

    public void setSessionTimeOut(int sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    public String getDeleteMethod() {
        return deleteMethod;
    }

    public void setDeleteMethod(String deleteMethod) {
        this.deleteMethod = deleteMethod;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }

    public Boolean getReceive() {
        return receive;
    }

    public void setReceive(Boolean receive) {
        this.receive = receive;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }
}
