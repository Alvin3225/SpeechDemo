package com.alvin.speechdemo.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by Administrator on 2016/10/18.
 */

public class JsonParser {
    public static String parseIatResult(String json){
        StringBuilder ret = new StringBuilder();
        try{
            JSONTokener tokener = new JSONTokener(json);
            JSONObject jsonObject = new JSONObject(tokener);
            JSONArray words = jsonObject.getJSONArray("ws");
            int len = words.length();
            for (int i=0;i<len;i++){
                //转写结果词，默认使用第一个结果
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                JSONObject object = items.getJSONObject(0);
                ret.append(object.getString("w"));
                //如果需要多候选结果，解析数组其他字段
				/*for(int j = 0; j < items.length(); j++){
					JSONObject obj = items.getJSONObject(j);
					ret.append(obj.getString("w"));
				}*/
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret.toString();
    }
}
