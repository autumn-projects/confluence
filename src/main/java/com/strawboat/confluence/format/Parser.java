package com.strawboat.confluence.format;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Deprecated
public class Parser {

    private String entity;
    private Map<String, Object> conditionMap;
    private Map<String, Object> orderMap;
    private Map<String, Object> dataMap;

    public Parser(String src) {
        JSONObject jo = new JSONObject(src);

        if (jo.has(Key.ENTITY)) {
            this.entity = jo.getString(Key.ENTITY);
        }

        if (jo.has(Key.CONDITION)) {
            this.conditionMap = getParameterMap(jo.getJSONObject(Key.CONDITION));
        }

        if (jo.has(Key.ORDER)) {
            this.orderMap = getParameterMap(jo.getJSONObject(Key.ORDER));
        }

        if (jo.has(Key.DATA)) {
            this.dataMap = getParameterMap(jo.getJSONObject(Key.DATA));
        }
    }

    private Map<String, Object> getParameterMap(JSONObject condition) {
        Map<String, Object> map = new LinkedHashMap<>();
        Set<String> keySet = condition.keySet();
        keySet.forEach(key -> map.put(key, condition.get(key)));
        return map;
    }

    public String getEntity() {
        return entity;
    }

    public Map<String, Object> getConditionMap() {
        return conditionMap;
    }

    public Map<String, Object> getOrderMap() {
        return orderMap;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }
}
