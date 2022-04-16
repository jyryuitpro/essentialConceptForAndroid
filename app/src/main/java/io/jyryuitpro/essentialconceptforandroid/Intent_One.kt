package io.jyryuitpro.essentialconceptforandroid

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        // 명시적 인텐트 + ComponentName -> 엑티비티 전환
        val intent_one: TextView = findViewById(R.id.intent_one)
        intent_one.setOnClickListener {
            val intent: Intent = Intent()
            val componentName: ComponentName = ComponentName(
                "io.jyryuitpro.essentialConceptForAndroid",
                "io.jyryuitpro.essentialConceptForAndroid.Intent_Two"
            )
            intent.component = componentName
            startActivity(intent)
        }

        // String을 사용하지 않고(위에서 처럼) 동일한 기능을 수행하는 코드
        // 명시적 인텐트  -> 엑티비티 전환
        // Context
        //  - 문맥
        // A엑티비티 -> B엑티비티
        // apply: 초기화
        // findViewById 위에 this? AppCompatActivity 즉 Context
        (findViewById<TextView>(R.id.intent_two)).apply {
            // apply 안에서 this? TextView
            this.setOnClickListener {
                startActivity(
                    // 출발지 -> 목적지
                    // this가 누군지 정확하게 표현해주어야 할 때, AppCompatActivity냐? TextView냐?
                    Intent(this@Intent_One, Intent_Two::class.java)
                )
            }
        }

        // 명시적 인텐트 + data 전달
        (findViewById<TextView>(R.id.intent_three)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                // key, value
                intent.putExtra("extra-data", "data-one")
                startActivity(intent)
            }
        }
    }
}