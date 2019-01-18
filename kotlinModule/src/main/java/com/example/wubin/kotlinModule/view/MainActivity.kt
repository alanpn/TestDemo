package com.example.wubin.kotlinModule.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.wubin.baselibrary.activity.BaseActivity
import com.example.wubin.kotlinModule.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

    }

    @OnClick(R.id.activity_main_btn, R.id.activity_main_toJava)
    fun onClick(v: View) {
        when (v.id) {
            R.id.activity_main_toJava -> {
                val intent = Intent(this, ToJavaActivity::class.java)
                startActivity(intent)
            }
            R.id.activity_main_btn -> {
            }

        }
    }

}