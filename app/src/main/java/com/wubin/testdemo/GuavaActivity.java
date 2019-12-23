package com.wubin.testdemo;

import android.os.Bundle;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.squareup.haha.guava.collect.ArrayListMultimap;
import com.squareup.haha.guava.collect.Multimap;

import java.util.List;
import java.util.Map;

/**
 * @author wubin
 * @description
 * @date 2019-12-09
 */

public class GuavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        collectionMethod();

        joinMethod();

        splitMethod();

    }

    private void collectionMethod() {

        List<String> list = Lists.newArrayList();
        Map<String, String> map = Maps.newHashMap();

        // 代替 Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("aa", 1);
        multimap.put("aa", 2);
        ShowUtil.print(multimap.get("aa"));  //[1, 2]

//        Table: 双键的Map Map--> Table-->rowKey+columnKey+value  //和sql中的联合主键有点像
        Table<String, String, Integer> tables = HashBasedTable.create();
        tables.put("a", "a", 1);
        tables.put("a", "b", 2);
        ShowUtil.print(tables.get("a", "a")); // 1

    }

    private void joinMethod() {

        // 将集合转换为特定规则的字符串
        List<String> joinerList = Lists.newArrayList("aa", "bb", "cc");
        ShowUtil.print(Joiner.on("-").join(joinerList)); // result为  aa-bb-cc

        Map<String, Integer> joinerMap = Maps.newLinkedHashMap();
        joinerMap.put("xiaoming", 12);
        joinerMap.put("xiaohong", 13);
        ShowUtil.print(Joiner.on(",").withKeyValueSeparator("=").join(joinerMap)); // result为 xiaoming=12,xiaohong=13
    }

    private void splitMethod() {

        // 将String转换为特定的集合
        List<String> splitToList = Splitter.on("-").splitToList("1-2-3-4-5-6"); //list为  [1, 2, 3, 4, 5, 6]
        ShowUtil.print(splitToList);

        // 不必考虑null
        Splitter splitNoNull = Splitter.on(",").trimResults().omitEmptyStrings();
        for (String str : splitNoNull.split("a,  ,b,c")) {
            ShowUtil.print(str); // 只有 a b c 三个元素
        }

        Map<String, String> map = Splitter.on(",").withKeyValueSeparator("=").split("xiaoming=11,xiaohong=23");
        ShowUtil.print(map);

    }
}
