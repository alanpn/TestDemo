package com.example.wubin.kotlinModule.view

import android.os.Bundle
import com.example.wubin.baselibrary.activity.BaseActivity
import com.example.wubin.baselibrary.util.ShowUtil
import com.example.wubin.kotlinModule.R
import kotlinx.android.synthetic.main.activity_kotlin.*

/**
 * @author wubin
 * @description
 * @date 2019-09-19
 */

class KotlinActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_kotlin)

        activity_main_toJava.setOnClickListener {
            println("xxxxx")
        }

        SharedPreferencesUtil.put(this, "aaa", "asdfsdf")
        ShowUtil.print("sdsfd : " + SharedPreferencesUtil.getString(this, "aaa"))

    }


}