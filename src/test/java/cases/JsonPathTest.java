package cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonPathTest {
    public static void main(String[] args) {

        String s = "{\"name\":\"wisdo\", \"age\":\"20\"}";


        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001, "ljw2083"));
        entities.add(new Entity(1002, "wenshao"));
        entities.add(new Entity(1003, "yakolee"));
        entities.add(new Entity(1004, null));
        List<List<Entity>> lists = Arrays.asList(entities);
        System.out.println(lists);
        System.out.println(entities);

        List<Object> result = (List<Object>) JSONPath.eval(entities, "[id in (109801,1004)]");
        Assert.assertEquals(1, result.size());
//        Assert.assertSame(entities.get(0), result.get(0));
        String jsonStr = "{ \"store\": " +
                "{\"book\": " +
                "[{ \"category\": \"reference\","+
                "\"author\": \"Nigel Rees\",\"title\": \"Sayings of the Century\","+
                "\"price\": 85.95}," +
                "{ \"category\": \"fiction\",\"author\": \"Evelyn Waugh\","+
                "\"title\": \"Sword of Honour\",\"price\": 12222.99,\"isbn\": \"0-553-21311-3\""+
                "}]," +
                "\"bicycle\": {\"color\": \"red\",\"price\": 19.95}}}";

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        int eval = (Integer) JSONPath.eval(jsonObject, "$.store.book.size()");
        System.out.println(eval);
        System.out.println("\n Book数目：" + JSONPath.eval(jsonObject, "$.store.book.size()"));
        System.out.println("第2本书作者：" + JSONPath.eval(jsonObject, "$.store.book[1].author"));
        System.out.println("第2本书作者：" + JSONPath.eval(jsonObject, "$.store.book[1]['author']"));
        System.out.println("第2本书作者：" + JSONPath.eval(jsonObject, "$.store.book[1]"));
        System.out.println("price大于10元的book：" + JSONPath.eval(jsonObject, "$.store.book[price > 10]['author','title']"));
        System.out.println("price大于10元的book：" + JSONPath.eval(jsonObject, "$.store.book[price > 10].author"));
        System.out.println("price大于10元的book：" + JSONPath.eval(jsonObject, "$.store.book[01]['isbn','category']"));
        System.out.println("price大于10元的book：" + JSONPath.eval(jsonObject, "$.store.book[01].isbn"));
        System.out.println("price小于12222.99元的title：" + JSONPath.eval(jsonObject, "$.store.book[price < 12222.99].title[0]"));
        System.out.println("category(类别)为fiction(小说)的book：" + JSONPath.eval(jsonObject, "$.store.book[category = 'fiction'][0].title"));
        System.out.println("bicycle的所有属性值" + JSONPath.eval(jsonObject, "$.store.bicycle.*"));

        System.out.println("bicycle的color和price属性值" + JSONPath.eval(jsonObject, "$.store.bicycle['color','price']"));

    }


    @Test(enabled = false)
    public void test_entity(){
        Entity entity = new Entity(123, new Object());

        Assert.assertSame(entity.getValue(), JSONPath.eval(entity, "$.value"));
        Assert.assertTrue(JSONPath.contains(entity, "$.value"));

        Assert.assertTrue(JSONPath.containsValue(entity, "$.id", 123));
        Assert.assertTrue(JSONPath.containsValue(entity, "$.value", entity.getValue()));
        Assert.assertEquals(2, JSONPath.size(entity, "$"));

    }

    public static class Entity {
        private Integer id;
        private String name;
        private Object value;

        public Entity() {}
        public Entity(Integer id, Object value) { this.id = id; this.value = value; }
        public Entity(Integer id, String name) { this.id = id; this.name = name; }
        public Entity(String name) { this.name = name; }

        public Integer getId() { return id; }
        public Object getValue() { return value; }
        public String getName() { return name; }

        public void setId(Integer id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setValue(Object value) { this.value = value; }

    }


//* ⌘N, ⌃↩, ⌃N 生成代码（getter、setter、构造函数、hashCode/equals,toString）





}
