package com.wubin.testdemo.json;

import com.example.wubin.baselibrary.util.ShowUtil;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonActivity {

    static class Bean {

        //      @SerializedName("name1")
//      @SerializedName(value = "name", alternate = "name1")
        @SerializedName(value = "name", alternate = {"name1", "name2"})
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) {

        String json = "{\"name2\":\"a\"}";

        System.out.print(new Gson().fromJson(json, Bean.class));

    }

    private void toJson() {
        try {

            JSONArray ports = new JSONArray();

            for (int i = 0; i < 3; i++) {

                JSONObject port = new JSONObject();

                port.put("portName", "M" + (i + 1) + "码头");

                JSONArray lines = new JSONArray();

                for (int j = 0; j < 3; j++) {
                    JSONObject line = new JSONObject();
                    line.put("lineName", "明珠" + (j + 1) + "号");
                    line.put("startTIme", "9:00");
                    line.put("endTIme", "19:00");
                    lines.put(line);
                }

                port.put("lines", lines);

                ports.put(port);

            }

            JSONObject reuslt = new JSONObject();
            reuslt.put("result", ports);

            ShowUtil.print(reuslt.toString());

        } catch (Exception e) {
        }
    }
}
