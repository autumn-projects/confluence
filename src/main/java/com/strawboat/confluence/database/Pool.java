package com.strawboat.confluence.database;

import com.strawboat.confluence.Entry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Pool {

    public Connection getInstance() throws SQLException {
        DataSourceConfig config = Entry.config;
        return DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
    }

}
