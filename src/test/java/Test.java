import com.strawboat.confluence.Entry;
import com.strawboat.confluence.format.Translator;
import com.strawboat.confluence.v1.SelectEntity;
import org.json.JSONObject;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        String DATABASE_URL = "localhost";
        String DATABASE_USER = "root";
        String DATABASE_PASSWORD = "123456";
        String driver = "com.mysql.jdbc.Driver";


        String src = "{\"entity\":\"addcarinfo\",\"condition\":{\"id\":\"1\"}}";
//        String src = "{\"entity\":\"v_area\",\"condition\":{\"Id\":1,\"name\":\"北京市\"}}";
//        Parser parser = new Parser(src);
//        System.out.println(parser.getEntity());
//        System.out.println(parser.getConditionMap());
//        System.out.println(parser.getOrderMap());
//
//        System.out.println(Translator.select(src));

        Entry entry = new Entry(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, driver);
        entityQuery(entry);

//        System.out.println(entry.findList(src));


//        System.out.println(translateInsert());
    }

    private static String translateInsert() {
        String src = "{\"entity\":\"area\",\"data\":{\"code\":\"9999\"}}";
        return Translator.insert(src);
    }

    private static void entityQuery(Entry entry) {
        String entity = "area";
        HashMap<String, Object> map = new HashMap<>();
        map.put("Id", 3);

        SelectEntity se = new SelectEntity(entity, map);
        JSONObject object = new JSONObject(se);
        System.out.println(entry.findList(object.toString()));
    }

}
