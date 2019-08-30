package com.example.wubin.baselibrary.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtil {

    private JsonUtil() {
    }

    public static JSONObject toObjectResult(JSONObject obj) throws Exception {
        return toObjectResult(obj, true);
    }

    /**
     * 解析 Json
     *
     * @param showing 是否需要弹消息
     */
    public static JSONObject toObjectResult(JSONObject obj, boolean showing) throws Exception {

        if (isOK(obj)) return toObject(obj);

        if (showing) showErrorMessage(obj);
        throw new Exception("失败");

    }

    public static String toStringResult(JSONObject obj) throws Exception {
        return toStringResult(obj, true);
    }

    public static String toStringResult(JSONObject obj, boolean isShow) throws Exception {

        if (isOK(obj)) return toString(obj);

        if (isShow) showErrorMessage(obj);
        throw new Exception("失败");

    }

    /**
     * 返回list数据
     */
    public static <T> List<T> toListResult(JSONObject obj, String key, Class<T> cls) throws Exception {
        return toList(toObjectResult(obj), key, cls);
    }

    /**
     * 返回bean数据
     */
    public static <T> T toBeanResult(JSONObject obj, String key, Class<T> cls) throws Exception {
        return toBean(toObjectResult(obj), key, cls);
    }

    //=====================================

    private static final String CODE = "0000";
    private static final String RESULT_CODE = "resultCode";
    private static final String RESULT = "result";
    private static final String RESULT_MESSAGE = "resultMessage";

    /**
     * 返回code 是否正确
     */
    private static boolean isOK(JSONObject obj) throws Exception {

        if (null == obj) return false;

        return CODE.equals(obj.getString(RESULT_CODE));

    }

    /**
     * json 是否为空
     */
    private static boolean isJsonEmpty(JSONObject obj) throws Exception {

        if (null == obj) return false;

        String result = obj.getString(RESULT).replaceAll("[${}]", "");
        return StringUtil.isNotBlank(result);

    }

    /**
     * 判断参数是否为空
     */
    private static void isParamEmpty(String json, Class cls) throws Exception {

        if (StringUtil.isBlank(json)) throw new Exception("json 没传");

        if (null == cls) throw new Exception("class 没传");

    }

    private static void isParamEmpty(JSONObject obj, Class cls) throws Exception {

        if (null == obj) throw new Exception("JSONObject 没传");
        if (null == cls) throw new Exception("class 没传");

    }

    private static void showErrorMessage(JSONObject obj) throws JSONException {

        String code = obj.getString(RESULT_CODE);
        String txt = obj.getString(RESULT_MESSAGE);
        ShowUtil.toastShow(txt);

    }

    /**
     * 获取有用json数据
     */
    private static String toString(JSONObject obj) throws Exception {
        return isOK(obj) && isJsonEmpty(obj) ? obj.getString(RESULT) : null;
    }

    private static JSONObject toObject(JSONObject obj) throws Exception {
        return isOK(obj) && isJsonEmpty(obj) ? obj.getJSONObject(RESULT) : null;
    }

    /**
     * 转list
     */
    private static <T> List<T> toList(JSONObject obj, String key, Class<T> cls) throws Exception {

        isParamEmpty(obj, cls);

        return toList(obj.getString(key), cls);

    }

    public static <T> List<T> toList(String json, Class<T> cls) throws Exception {

        List<T> list = new ArrayList<>();

        try {
            String input = json.replaceAll("\\[|\\]", "");
            isParamEmpty(input, cls);
            Gson gson = GsonUtil.create();
            JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                list.add(gson.fromJson(jsonElement, cls));
            }
            return list;
        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return null;
    }

    public static <T> List<T> toList_A(String json, Class<T[]> cls) throws Exception {

        isParamEmpty(json, cls);

        T[] array = GsonUtil.create().fromJson(json, cls);
        return Arrays.asList(array);

    }

    /**
     * 转Bean
     */
    private static <T> T toBean(JSONObject obj, String key, Class<T> cls) throws Exception {
        return toBean(obj.getString(key), cls);
    }

    private static <T> T toBean(String json, Class<T> cls) throws Exception {

        isParamEmpty(json, cls);

        return GsonUtil.create().fromJson(json, cls);

    }

}
