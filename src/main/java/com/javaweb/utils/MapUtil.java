package com.javaweb.utils;

import java.text.SimpleDateFormat;
import java.util.Map;
public class MapUtil {
    public static <T> T getObject(Map<String, Object> params, String key, Class<T> tClass){
        Object obj = params.getOrDefault(key,null);
        if(obj != null){
            try {
                if (tClass.getTypeName().equals("java.lang.Long")) {
                    obj = obj != "" ? Long.valueOf(obj.toString()) : null;
                } else if (tClass.getTypeName().equals("java.lang.Integer")) {
                    obj = obj != "" ? Integer.valueOf(obj.toString()) : null;
                } else if (tClass.getTypeName().equals("java.lang.String")) {
                    obj = obj.toString();
                }
                else if (tClass.getTypeName().equals("java.lang.Double")) {
                    obj = obj != "" ? Double.valueOf(obj.toString()) : null;
                }
                    else if (tClass.getTypeName().equals("java.util.Date")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // chỉnh định dạng theo frontend gửi
                    obj = sdf.parse(obj.toString());
                }
                return tClass.cast(obj);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
