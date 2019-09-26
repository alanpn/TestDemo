package com.example.wubin.kotlinModule.view

import android.content.Intent
import android.os.Bundle
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.wubin.baselibrary.activity.BaseActivity
import com.example.wubin.kotlinModule.R

/**
 * @author wubin
 * @description
 * @date 2019-09-19
 */

class KotlinActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_kotlin)

        ButterKnife.bind(this)

    }

    @OnClick(R.id.activity_main_toJava)
    fun toJava() {
        val intent = Intent(this, ToJavaActivity::class.java)
        startActivity(intent)
    }

}