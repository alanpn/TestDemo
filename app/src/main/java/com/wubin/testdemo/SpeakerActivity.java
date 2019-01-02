package com.wubin.testdemo;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;

import java.util.Locale;

/**
 * 语音播报 利用手机自带语音
 */
public class SpeakerActivity extends BaseActivity
        implements View.OnClickListener, TextToSpeech.OnInitListener {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_speaker);

        findViewById(R.id.activity_speaker_btn).setOnClickListener(this);

        tts = new TextToSpeech(this, this);

        // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
        tts.setPitch(1.0f);

        // 设置语速
        tts.setSpeechRate(1.3f);

    }

    @Override
    public void onClick(View v) {

//        if (editText.getText().length() >= 1) {
//            tts.speak(editText.getText().toString(),
//                    TextToSpeech.QUEUE_FLUSH, null);
//        } else {
//            tts.speak("Nothing to say", TextToSpeech.QUEUE_FLUSH, null);
//        }


        tts.speak("支付宝到账3911.05元", TextToSpeech.QUEUE_FLUSH, null);

    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            // Toast.makeText(MainActivity.this,"成功输出语音",
            // Toast.LENGTH_SHORT).show();
            // Locale loc1=new Locale("us");
            // Locale loc2=new Locale("china");
            int result1 = tts.setLanguage(Locale.US);
            int result2 = tts.setLanguage(Locale.CHINESE);
            if (result1 == TextToSpeech.LANG_MISSING_DATA
                    || result1 == TextToSpeech.LANG_NOT_SUPPORTED
                    || result2 == TextToSpeech.LANG_MISSING_DATA
                    || result2 == TextToSpeech.LANG_NOT_SUPPORTED) {
                ShowUtil.toastShow("数据丢失或不支持");
            }
        }

    }

    @Override
    protected void onDestroy() {

        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();
    }


}
