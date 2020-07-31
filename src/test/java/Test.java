import com.strawboat.confluence.executor.Entry;
import com.strawboat.confluence.entity.Select;
import com.strawboat.confluence.entity.Update;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        String DATABASE_URL = "jdbc:mysql://47.106.15.47:3306/preissue_service_bloated?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=utf-8";
        String DATABASE_USER = "root";
        String DATABASE_PASSWORD = "123456";
        String driver = "com.mysql.jdbc.Driver";

        Entry entry = new Entry(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, driver);
        update(entry);
        entityQuery(entry);
    }

    private static void entityQuery(Entry entry) {
        String entity = "area";
        HashMap<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("sheng", 11);
        HashMap<String, Object> orderMap = new HashMap<>();
        orderMap.put("Id", "desc");
        orderMap.put("di", "asc");
        HashMap<String, Object> pageMap = new HashMap<>();
        pageMap.put("page", 1);
        pageMap.put("size", 10);

        Select select = new Select();
        select.setEntity(entity);
        select.setConditionMap(conditionMap);
        select.setOrderMap(orderMap);
        select.setPageMap(pageMap);
        System.out.println(entry.getList(select));
        System.out.println(entry.getCount(select));
    }

    private static void update(Entry entry) {
        String entity = "area";
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "房山区1");
        HashMap<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("Id", 10);

        Update update = new Update();
        update.setEntity(entity);
        update.setDataMap(dataMap);
        update.setConditionMap(conditionMap);
        entry.update(update);
    }

}
