package cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.JsonPathAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {


        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("UserName", "ZHULI");
        hashMap.put("age", "30");
        hashMap.put("workIn", "ALI");
        System.out.println("jsonObject2：" + JSON.toJSONString(hashMap));
        String jsonString = JSON.toJSONString(hashMap);
        JSONObject jsonObject2 = JSONObject.parseObject(jsonString);

        Object age = jsonObject2.getString("age");
        Object userName = jsonObject2.getString("UserName");

        JsonPathAssert jsonPathAssert = new JsonPathAssert();
        String json1 = JSON.toJSONString(jsonPathAssert);
        JSONObject jsonObject1 = JSONObject.parseObject(json1);
        Object value = jsonObject1.get("value");
        System.out.println(value);

        String json = "{\"data\":{\"items\":[{\"itemstring\":\"手机\",\"itemcoord\":{\"x\":0,\"y\":100,\"width\":40,\"height\":20},},{\"itemstring\":\"手机\",\"itemcoord\":{\"x\":0,\"y\":100,\"width\":40,\"height\":20},}],\"session_id\":\"\",},\"code\":0,\"message\":\"OK\"}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        Object message = jsonObject.get("message");
        Object code = jsonObject.get("code");
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray items = data.getJSONArray("items");
        String arr = "[{\"itemstring\":\"手99机\",\"itemcoord\":{\"x\":0,\"y\":100,\"width\":40,\"height\":20},},{\"itemstring\":\"手机\",\"itemcoord\":{\"x\":0,\"y\":100,\"width\":40,\"height\":20},}]";
        JSONArray array = JSONArray.parseArray(arr);

        JSONArray jsonArrayRow = new JSONArray();
        jsonArrayRow.add("json");
        jsonArrayRow.add("arr");
        String jsonString1 = JSON.toJSONString(jsonArrayRow);
        System.out.println(jsonString1);
        for (Object o : array) {

        }
        JSONObject row = null;
        for(int i=0; i<items.size(); i++){
            row = items.getJSONObject(i);
            System.out.println("itemstring ：" + row.get("itemstring"));
//            JSONObject itemcoord = row.getJSONObject("itemcoord");
            JSONObject itemcoord = row.getJSONObject("itemcoord");
            System.out.println("x：" + itemcoord.get("x"));
            System.out.println("y：" + itemcoord.get("y"));
            System.out.println("width：" + itemcoord.get("width"));
            System.out.println("height：" + itemcoord.get("height"));
        }
        System.out.println("session_id：" + data.get("session_id"));
        System.out.println("code：" + jsonObject.get("code"));


        System.out.println("----------------------------------------");
        List<String> contentList = new ArrayList<>();
        contentList.add("[\"HDC-51\"]");
        contentList.add("[\"HDC-51\", \"HDC-55\"]");
        contentList.add("[\"HDC-50\", \"HDC-55\", \"HDC-55-2\"]");
        contentList.add("[\"HDC-51\", \"HDC-55\", \"HDC-55-2\",\"HDC-21N\"]");

        System.out.println(contentList);
        String macType = "HDC-50";

        for (String content : contentList) {

            try {
                JSONArray contentArray = JSONArray.parseArray(content);

                //System.out.println("contentArray前 : " + contentArray);
                if (!contentArray.contains(macType)) {
                    contentArray.add(macType);
                }
                System.out.println("contentArray后 : " + contentArray);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

//        for (String content : contentList) {
//            System.out.println(content);
//            //去掉content 中的中括号
//            String contentStr1 = content.replaceAll("[\\[\\]]", "");
//            System.out.println(contentStr1);
//
//            List<String> content1 = Arrays.asList(contentStr1.split(","));
//
//            List<String> list = new ArrayList<>();
//
//            for (String string : content1) {
//                list.add(string);
//            }
//
//            System.out.println(list);
//
//            //判断content中是否已经包含macType
//            boolean flag = false;
//            for (String string : list) {
//                //去掉字符串的引号
//                String str= string.replace("\"", "");
//                if (macType.equals(str)) {
//                    flag = true;
//                    break;
//                }
//            }
//
//            //如果没有macType,则添加
//            if (flag == true) {
//                StringBuilder sb = new StringBuilder();
////                String macTypeStr = sb.append("\"").append(macType).append("\"").toString();
//                String macTypeStr = sb.append(macType).toString();
//                list.add(macTypeStr);
//            }
//
//            String newContent = list.toString();
//            System.out.println("=========================");
//            System.out.println(newContent);
//
//        }
    }
}
