package com.strawboat.confluence.v1;

import com.strawboat.confluence.database.Pool;
import com.strawboat.confluence.entity.Select;
import com.strawboat.confluence.format.Name;
import com.strawboat.confluence.format.Translator;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Executor {

//    public Map<String, Object> findOne(String src) {
//        Map<String, Object> map = new HashMap<>();
//
//        try (Connection connection = new Pool().getInstance();
//             Statement statement = connection.createStatement()) {
//
//            statement.setMaxRows(1);
//            String sql = Translator.select(src);
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            if (resultSet.next()) {
//                map = resultSetToMap(resultSet);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return map;
//    }

    public List<Map<String, Object>> findList(Select select) {
        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection connection = new Pool().getInstance();
             Statement statement = connection.createStatement()) {

            String sql = Translator.select(select);
            System.out.println(sql);
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
