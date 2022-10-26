package ru.stazaev.site.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FormatConverter {
    private Map<String, Object> jsonObjectMap = new HashMap<>();

    public String getJsonValueByString(String string, String key){
        if (jsonObjectMap.isEmpty()){
            JSONObject jsonObject = new JSONObject(string);
            this.jsonObjectMap = jsonObject.toMap();
        }else if (!jsonObjectMap.containsKey(key)){
            jsonObjectMap.put(key, new JSONObject(string).getString(key));
        }
        if (!jsonObjectMap.get(key).equals(new JSONObject(string).getString(key))){
            jsonObjectMap.put(key, new JSONObject(string).getString(key));
        }


        JSONObject jsonObject = new JSONObject(string);
        return jsonObject.getString(key);
    }

    public JSONObject convertToJson(int value, String name){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(name,value);
        return jsonObject;
    }
    public JSONObject convertToJson(double value, String name){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(name,value);
        return jsonObject;
    }

    public JSONObject convertToJson(String value, String name){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(name,value);
        return jsonObject;
    }

    public JSONObject convertListToJson(List<List<Integer>> list, String name){
        JSONArray externalArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            externalArray.put(new JSONArray(list.get(0)));
        }
        return new JSONObject().put(name,externalArray);
    }

    public boolean isContains(String key){
        return jsonObjectMap.containsKey(key);
    }

    public String convertValueToXML(String name,double value ){
        return "<?xml version=1.0 encoding=UTF-8?>\n" +
                "<root>\n" +
                "<" + name + ">" + value + "</" + name + ">\n" +
                "</root>\n";
    }

    public String convertValueToXML(String name,String value ){
        return "<?xml version=1.0 encoding=UTF-8?>\n" +
                "<root>\n" +
                "<" + name + ">" + value + "</" + name + ">\n" +
                "</root>\n";
    }

    public String convertListToXML(String name, List<List<Integer>> list){
        StringBuilder result = new StringBuilder("<?xml version=1.0 encoding=UTF-8?>\n" +
                "<root>\n" +
                "<" + name + ">\n");
        for (int i = 0; i < list.size(); i++) {
            result.append("<elements>\n");
            for (int j = 0; j < list.get(i).size(); j++) {
                result.append("<element>").append(list.get(i).get(j)).append("<element/>\n");
            }
            result.append("<elements/>\n");
        }
        result.append("<").append(name).append("/>\n");
        result.append("<root/>\n");
        return result.toString();
    }
}
