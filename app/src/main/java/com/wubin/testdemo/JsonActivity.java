package com.wubin.testdemo;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

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
}
