package cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User[" +
                "name='" + name + '\'' +
                ", age=" + age +
                ']';
    }
}
class UserGroup {
    private String name;
    private List<User> users = new ArrayList<User>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserGroup 【name=" + name + ", users=" + users + "】";
    }
}

class FastJsonTest {
    public static void main(String[] args) {
        // 构建用户geust
        User guestUser = new User();
        guestUser.setName("guest");
        guestUser.setAge(28);
        // 构建用户root
        User rootUser = new User();
        rootUser.setName("root");
        guestUser.setAge(35);
        // 构建用户组对象
        UserGroup group = new UserGroup();
        group.setName("admin");
        group.getUsers().add(guestUser);
        group.getUsers().add(rootUser);
        // 用户组对象转JSON串
        String jsonString = JSON.toJSONString(group);
        System.out.println("jsonString:" + jsonString);
        // JSON串转用户组对象
        UserGroup group2 = JSON.parseObject(jsonString, UserGroup.class);
        System.out.println("group2:" + group2);

        // 构建用户对象数组
        User[] users = new User[2];
        users[0] = guestUser;
        users[1] = rootUser;
        // 用户对象数组转JSON串
        String jsonString2 = JSON.toJSONString(users);
        System.out.println("jsonString2:" + jsonString2);
        // JSON串转用户对象列表
        List<User> users2 = JSON.parseArray(jsonString2, User.class);
        System.out.println("users2:" + users2);
    }
}

class TestFastJson {

    static class Person{
        private String id ;
        private String name;
        private int age ;

        public Person(){

        }
        public Person(String id,String name,int age){
            this.id=id;
            this.name=name;
            this.age=age;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        @Override
        public String toString() {
            return "Person [age=" + age + ", id=" + id + ", name=" + name + "]";
        }


    }
    public static void main(String[] args) {
        method1();
        method2();
//        method3();
//        method4();
    }

    static void method1(){
        System.out.println("javabean转化示例开始----------");
        Person person = new Person("1","fastjson",1);

        //这里将javabean转化成json字符串
        String jsonString = JSON.toJSONString(person);
        System.out.println(jsonString);
        //这里将json字符串转化成javabean对象,
        person =JSON.parseObject(jsonString,Person.class);
        System.out.println(person.toString());

        System.out.println("javabean转化示例结束----------");
    }

    static void method2(){
        System.out.println("List<javabean>转化示例开始----------");

        Person person1 = new Person("1","fastjson1",1);
        Person person2 = new Person("2","fastjson2",2);
        List<Person> persons = new ArrayList<Person>();
        persons.add(person1);
        persons.add(person2);
        String jsonString = JSON.toJSONString(persons);
        System.out.println("json字符串:"+jsonString);

        //解析json字符串
        List<Person> persons2 = JSON.parseArray(jsonString,Person.class);
        //输出解析后的person对象，也可以通过调试模式查看persons2的结构
        System.out.println("person1对象："+persons2.get(0).toString());
        System.out.println("person2对象："+persons2.get(1).toString());

        System.out.println("List<javabean>转化示例结束----------");
    }
}

class Test {

    public static void main(String[] args) {
        String jsonString = "{\"UserName\":\"ZHULI\",\"age\":\"30\",\"workIn\":\"ALI\",\"Array\":[\"ZHULI\",\"30\",\"ALI\"]}";
        //将Json字符串转为java对象
        JSONObject obj = JSONObject.parseObject(jsonString);
        //获取Object中的UserName
        if (obj.containsKey("UserName")) {
            System.out.println("UserName:" + obj.get("UserName"));
        }
        //获取ArrayObject
        if (obj.containsKey("Array")) {
            JSONArray transitListArray = obj.getJSONArray("Array");
            for (int i = 0; i < transitListArray.size(); i++) {
                System.out.print("Array:" + transitListArray.getString(i) + " ");
            }
        }
    }
}