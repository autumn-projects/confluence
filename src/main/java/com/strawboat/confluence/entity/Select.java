package com.strawboat.confluence.entity;

import java.util.HashMap;
import java.util.Map;

public class Select {

    private String entity;
    private Map<String, Object> conditionMap;
    private Map<String, Object> sortMap;
    private Pagination pagination;

    public Select() {

    }

    public void addCondition(String key, Object value) {
        if (conditionMap == null) {
            conditionMap = new HashMap<>();
        }
        conditionMap.put(key, value);
    }

    public void addSort(String key, Object value) {
        if (sortMap == null) {
            sortMap = new HashMap<>();
        }
        sortMap.put(key, value);
    }

    public void addPagination(int page, int size) {
        this.pagination = new Pagination(page, size);
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
        return sortMap;
    }

    public void setOrderMap(Map<String, Object> orderMap) {
        this.sortMap = orderMap;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
