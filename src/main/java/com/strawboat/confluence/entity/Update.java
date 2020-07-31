package com.strawboat.confluence.entity;

import java.util.HashMap;
import java.util.Map;

public class Update {

    private String entity;
    private Map<String, Object> dataMap;
    private Map<String, Object> conditionMap;

    public Update() {

    }

    public void addData(String key, Object value) {
        if(dataMap == null) {
            dataMap = new HashMap<>();
        }
        dataMap.put(key, value);
    }

    public void addCondition(String key, Object value) {
        if(conditionMap == null) {
            conditionMap = new HashMap<>();
        }
        conditionMap.put(key, value);
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Map<String, Object> getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(Map<String, Object> conditionMap) {
        this.conditionMap = conditionMap;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
