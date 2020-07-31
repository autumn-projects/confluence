package com.strawboat.confluence.entity;

import java.util.Map;

public class Delete {

    private String entity;
    private Map<String, Object> conditionMap;

    public Delete() {

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

}
