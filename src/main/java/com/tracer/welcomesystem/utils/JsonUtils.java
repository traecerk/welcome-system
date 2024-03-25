package com.tracer.welcomesystem.utils;
import com.alibaba.fastjson.*;

public class JsonUtils {
    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }


}
