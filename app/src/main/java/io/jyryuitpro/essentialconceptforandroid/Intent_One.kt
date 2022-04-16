package io.jyryuitpro.essentialconceptforandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Intent_One : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_one)

        // 암시적 인텐트
        // - 전화, SMS, Google Play Store, Website, Google Map, 사진첩 등등
        // - 상수
        //      - 변하지 않는 수(문자)
        //      - 상수의 변수명은 전부 대문자로 만드는 문화가 있다
        // - URI (Uniform Resource Identifier)
        //      - id라고 생각을 하면 된다 -> 고유하다
        //      - 자원을 나타내는 고유한 주소
        // - URL
        //      - 인터넷 페이지의 고유한 주소
        val implicit_intent: TextView = findViewById(R.id.implicit_intent)
        implicit_intent.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "010-9378-9025"))
//            val intent: Intent = Intent(Intent.ACTION_PICK) -> 사진첩
            startActivity(intent)
        }
    }
}