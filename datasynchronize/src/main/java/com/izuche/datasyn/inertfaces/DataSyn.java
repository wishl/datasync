package com.izuche.datasyn.inertfaces;

import java.io.Serializable;

public interface DataSyn {

    Serializable get(String key,Class<?> require);

    void set(String key,Object value);

    void send(String key,Object value);

    void delete(String key);

}
