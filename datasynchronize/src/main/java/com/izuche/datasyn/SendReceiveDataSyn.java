package com.izuche.datasyn;

import com.izuche.datasyn.abstracts.AbstractReceive;
import com.izuche.datasyn.common.NameUtils;
import com.izuche.datasyn.properties.DataSynProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SendReceiveDataSyn extends AbstractReceive implements BeanFactoryAware {

    private BeanFactory beanFactory;
    private Object bean;

    public SendReceiveDataSyn(DataSynProperties dataSynProperties) {
        super(dataSynProperties);
        String sendMethod = dataSynProperties.getSendMethod();
        Assert.hasText(sendMethod,"接收数据方法不能为空");
        String deleteMethod = dataSynProperties.getDeleteMethod();
        Assert.hasText(deleteMethod,"删除数据方法不能为空");
        Class sendClass = dataSynProperties.getSendClass();
        Assert.notNull(sendClass,"接收类不能为空");
        listener();
    }

    @Override
    public void send(String key, Object value) {
        key = NameUtils.getKey(key);
        Class<?> sendClass = getSendClass();
        String sendMethod = getSendMethod();
        Method method = getMethod(sendClass, sendMethod,String.class,Object.class);
        if(method != null){
            try {
                method.invoke(getBean(sendClass),key,value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }else{
            throw new RuntimeException("方法不存在");
        }
    }

    private Method getMethod(Class<?> clazz,String method,Class...require){
        Method method1 = null;
        try {
            method1 = clazz.getMethod(method,require);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(method1==null){
            return null;
        }
        return method1;
    }

    @Override
    public void delete(String key) {
        key = NameUtils.getKey(key);
        Class<?> sendClass = getSendClass();
        String deleteMethod = getDeleteMethod();
        Method method = getMethod(sendClass, deleteMethod,String.class);
        if(method != null){
            try {
                method.invoke(getBean(sendClass),key);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }else{
            throw new RuntimeException("方法不存在");
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public Object getBean(Class require) throws IllegalAccessException, InstantiationException {
        if(bean == null) {
            bean = beanFactory.getBean(require);
        }
        if(bean==null){
            synchronized (this){
                if(bean==null){
                    this.bean = require.newInstance();
                }
            }
        }
        return bean;
    }
}
