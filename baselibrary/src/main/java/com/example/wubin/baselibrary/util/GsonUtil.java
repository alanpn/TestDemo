package com.example.wubin.baselibrary.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;

public class GsonUtil {

    public static Gson create() {

        if (null != gson) {
            return gson;
        }

        GsonBuilder builder = new GsonBuilder();

        // 对日期类型数据进行格式化
        builder
                .registerTypeAdapter(Date.class, new DateFormat())
                .registerTypeAdapter(java.sql.Date.class, new DateFormat())
                .registerTypeAdapter(Timestamp.class, new DateFormat());

        // 对字符串类型进行格式化
        builder.registerTypeAdapter(String.class, new StringFormat());

        gson = builder.create();

        return gson;

    }

    private static class DateFormat implements JsonSerializer<Date>, JsonDeserializer<Date> {

        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {

            if (null == src) src = new Date();
            return new JsonPrimitive(TimeUtil.format(src, 0));

        }

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            if (null == json) return new Date();
            return TimeUtil.format(getString(json), 0);

        }
    }

    private static class StringFormat implements JsonSerializer<String>, JsonDeserializer<String> {

        @Override
        public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {

            if (StringUtil.isEmpty(src)) src = "";
            return new JsonPrimitive(src);

        }

        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            if (null == json) return "";
            return getString(json);

        }

    }

    //===========================

    private static Gson gson;

    private static String getString(JsonElement json) {
        return json.getAsJsonPrimitive().getAsString();
    }

}