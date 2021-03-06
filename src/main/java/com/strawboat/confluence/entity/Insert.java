package com.strawboat.confluence.entity;

import java.util.HashMap;
import java.util.Map;

public class Insert {

    private String entity;
    private Map<String, Object> dataMap;

    public Insert() {

    }

    public void addData(String key, Object value) {
        if(dataMap == null) {
            dataMap = new HashMap<>();
        }
        dataMap.put(key, value);
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
