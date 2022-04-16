package io.jyryuitpro.essentialconceptforandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class Intent_Two : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_two)

        // Intent를 만든게 아니라 그냥 가져온 거임
        // intent는 Context안에 있었음
        // Intent_Two엑티비티를 호출한 Activity의 intent이다
        val intent = intent
        // 넘겨준 data가 없을 수 도 있어서 nullable string
        val data: String? = intent.extras?.getString("extra-data")

        if (data != null) {
            Log.d("dataa", data)
        }
    }
}