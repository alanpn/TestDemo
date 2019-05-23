package com.example.wubin.kotlinModule.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.wubin.baselibrary.activity.BaseActivity
import com.example.wubin.kotlinModule.R

class KotlinActivity : BaseActivity() {

    val ID_BTN = R.id.activity_main_btn
    val ID_TO_JAVA = R.id.activity_main_toJava

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_kotlin)

        ButterKnife.bind(this)

    }

    @OnClick(ID_BTN, ID_TO_JAVA)
    fun onClick(v: View) {
        when (v.id) {

            ID_TO_JAVA -> {
                val intent = Intent(this, ToJavaActivity::class.java)
                startActivity(intent)
            }

            ID_BTN -> {
            }

        }
    }

}