package com.strawboat.confluence.entity;

import java.util.HashMap;
import java.util.Map;

public class Select {

    private String entity;
    private Map<String, Object> conditionMap;
    private Map<String, Object> orderMap;
    private Map<String, Object> pageMap;

    public Select() {

    }

    public Select(String entity, HashMap<String, Object> conditionMap) {
        this.entity = entity;
        this.conditionMap = conditionMap;
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

    public Map<String, Object> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<String, Object> orderMap) {
        this.orderMap = orderMap;
    }

    public Map<String, Object> getPageMap() {
        return pageMap;
    }

    public void setPageMap(Map<String, Object> pageMap) {
        this.pageMap = pageMap;
    }

}
