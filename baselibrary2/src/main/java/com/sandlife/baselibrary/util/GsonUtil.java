package com.sandlife.baselibrary.util;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GsonUtil {
	public static Gson create(){
		Gson gson=new GsonBuilder()
		.registerTypeAdapter(Date.class, new DateTimeSeri())
		.registerTypeAdapter(java.sql.Date.class, new DateTimeSeri())
		.registerTypeAdapter(Timestamp.class, new DateTimeSeri())
		.create();
		return gson;
	}
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static class DateTimeSeri implements JsonSerializer<Date>,JsonDeserializer<Date> {
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(format.format(src));
		}
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			try {
				return format.parse(json.getAsJsonPrimitive().getAsString());
			} catch (ParseException e) {
				throw new JsonParseException(e.getMessage());
			}
		}
	}
}