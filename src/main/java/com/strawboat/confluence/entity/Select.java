package com.strawboat.confluence.entity;

import java.util.HashMap;

public class Select {

    private String entity;
    private HashMap<String, Object> condition;


    public Select() {

    }

    public Select(String entity, HashMap<String, Object> condition) {
        this.entity = entity;
        this.condition = condition;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public HashMap<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(HashMap<String, Object> condition) {
        this.condition = condition;
    }

}
