package com.izuche.datasyn.common;

import org.springframework.util.Assert;

import java.io.*;

public class ByteToObject {


    public static byte[] Object2Byte(Object object) throws IOException {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bos!=null){
                bos.close();
            }
            if(oos!=null){
                oos.close();
            }
        }
        return null;
    }


    public static<T> T Byte2Object(byte[] bytes,Class<T> require) throws IOException {
        ObjectInputStream ois = null;
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            Object object = ois.readObject();
            Assert.isInstanceOf(require,object,"转型失败");
            return require.cast(object);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(bis!=null){
                bis.close();
            }
            if(ois!=null){
                ois.close();
            }
        }
        return null;
    }

}
