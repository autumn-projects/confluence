package com.strawboat.confluence.executor;

import com.strawboat.confluence.entity.Delete;
import com.strawboat.confluence.entity.Insert;
import com.strawboat.confluence.entity.Select;
import com.strawboat.confluence.entity.Update;
import com.strawboat.confluence.format.Translator;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Entry {

    private String url;
    private String user;
    private String password;

    public Entry(String url, String user, String password, String driver) {
        try {
            Class.forName(driver);
            this.url = url;
            this.user = user;
            this.password = password;
            getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public List<Map<String, Object>> getList(Select select) {
        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String sql = Translator.select(select);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Map<String, Object> map = resultSetToMap(resultSet);
                list.add(map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Long getCount(Select select) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String sql = Translator.count(select);
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return resultSet.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0L;
    }

    public void update(Update update) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String sql = Translator.update(update);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Insert insert) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String sql = Translator.insert(insert);
            System.out.println(sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Delete delete) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String sql = Translator.delete(delete);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> resultSetToMap(ResultSet resultSet) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int count = metaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            String key = Name.lineToHump(metaData.getColumnLabel(i));
            Object value = resultSet.getObject(i);
            map.put(key, value);
        }
        return map;
    }

}
