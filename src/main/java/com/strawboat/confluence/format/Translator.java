package com.strawboat.confluence.format;

import com.strawboat.confluence.entity.Select;

import java.util.Map;
import java.util.Set;

public class Translator {

    public static String select(Select select) {
        String entity = select.getEntity();
        Map<String, Object> conditionMap = select.getConditionMap();
        Map<String, Object> orderMap = select.getOrderMap();
        Map<String, Object> pageMap = select.getPageMap();

        StringBuilder sb = new StringBuilder();
        sb.append(" select *");
        sb.append(String.format(" from %s", entity));

        if (conditionMap != null) {
            sb.append(whereSentence(conditionMap));
        }

        if (orderMap != null) {
            Set<String> keySet = orderMap.keySet();
            sb.append(" order by ");
            keySet.forEach(key -> {
                Object value = orderMap.get(key);
                sb.append(String.format("%s %s,", key, value));
            });
            sb.deleteCharAt(sb.length() - 1);
        }

        if (pageMap != null) {
            int page = (int) (pageMap.get(Key.PAGE));
            int size = (int) (pageMap.get(Key.SIZE));

            page = page - 1 < 0 ? 0 : page - 1;
            int offset = page * size;
            sb.append(String.format(" limit %d,%d", offset, size));
        }

        return sb.toString();
    }

    public static String update(String src) {
        String sql = "update %s set %s where %s";

        Parser parser = new Parser(src);
        String entity = parser.getEntity();
        Map<String, Object> dataMap = parser.getDataMap();
        Map<String, Object> conditionMap = parser.getConditionMap();

        if (dataMap == null) throw new RuntimeException("data is null");

        StringBuilder data = new StringBuilder();
        dataMap.forEach((key, value) -> {
            if (value instanceof String) {
                data.append(String.format("%s = '%s'", key, value));
            } else {
                data.append(String.format("%s = %s", key, value));
            }
        });

        StringBuilder condition = new StringBuilder();
        condition.append(" where 1 = 1");
        if (conditionMap != null) {
            Set<String> keySet = conditionMap.keySet();
            keySet.forEach(key -> {
                Object value = conditionMap.get(key);
                if (value instanceof Integer || value instanceof Double) {
                    condition.append(String.format(" and %s = %s", key, value));
                } else if (value instanceof String) {
                    condition.append(String.format(" and %s = '%s'", key, value));
                } else {
                    condition.append(String.format(" and %s is null", key));
                }
            });
        }

        return String.format(sql, entity, data, condition);
    }

    public static String insert(String src) {
        Parser parser = new Parser(src);
        String entity = parser.getEntity();
        Map<String, Object> dataMap = parser.getDataMap();
        if (dataMap == null) throw new RuntimeException("data is null");

        StringBuilder keys = new StringBuilder();
        StringBuilder values = new StringBuilder();

        dataMap.forEach((key, value) -> {
            keys.append(String.format("`%s`,", key));
            if (value instanceof Integer || value instanceof Double) {
                values.append(String.format("'%s',", value));
            } else if (value instanceof String) {
                values.append(String.format("%s,", value));
            } else {
                values.append("null,");
            }
        });

        return String.format("insert into %s(%s) values (%s)", entity,
                keys.substring(0, keys.length() - 1), values.substring(0, values.length() - 1));
    }

    private static String whereSentence(Map<String, Object> conditionMap) {
        if (conditionMap == null) return null;

        StringBuilder sb = new StringBuilder();
        sb.append(" where 1 = 1");
        Set<String> keySet = conditionMap.keySet();
        keySet.forEach(key -> {
            Object value = conditionMap.get(key);
            String where;
            if (value instanceof Integer || value instanceof Double) {
                where = String.format(" and %s = %s", key, value);
            } else if (value instanceof String) {
                where = String.format(" and %s = '%s'", key, value);
            } else {
                where = String.format(" and %s is null", key);
            }
            sb.append(where);
        });
        return sb.toString();
    }

}
