package com.foody.payment.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = -1377225889451120985L;

    private boolean ok;
    private String msg;


    private Map<String, Object> data;


    /* helpers for data */
    public Object get(String key) {
        if (this.data == null)
            return null;
        return data.get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        if (this.data == null)
            return null;
        return (T) data.get(key);
    }

    public void add(String key, Object obj) {
        if (data == null)
            data = new HashMap<>();
        data.put(key, obj);
    }

    public void addNonNull(String key, Object obj) {

        if (obj != null)
            add(key, obj);
    }

    public void addAll(Map<String, Object> content) {
        if (content == null)
            return;
        if (data == null)
            data = new HashMap<>();
        data.putAll(content);
    }
}
