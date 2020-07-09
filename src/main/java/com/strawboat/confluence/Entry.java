package com.strawboat.confluence;

import com.strawboat.confluence.database.DataSourceConfig;
import com.strawboat.confluence.v1.Executor;

import java.util.List;
import java.util.Map;


public class Entry {

    public static DataSourceConfig config;
    private Executor executor = new Executor();

    public Entry(String url, String user, String password, String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        config = new DataSourceConfig(url, user, password);
    }

    public Map<String, Object> findOne(String src) {
        return executor.findOne(src);
    }

    public List<Map<String, Object>> findList(String src) {
        return executor.findList(src);
    }

}
