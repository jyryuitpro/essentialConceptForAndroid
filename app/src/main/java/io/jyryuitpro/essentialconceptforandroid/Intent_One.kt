package io.jyryuitpro.essentialconceptforandroid

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

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

        // 명시적 인텐트 + 결과 받기
        // requestCode
        // - 보통 상수로 관리를 해준다
        // - 결과값을 받는 곳에서 구분을 하기 위해서
        // - Intent_One -> Intent_Two (request 1)
        // - Intent_One -> Intent_Three (request 2)
        // - Intent_One -> Intent_Four (request 3)
        (findViewById<TextView>(R.id.intent_four)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                startActivityForResult(intent, 1) //deprecated 되었다
            }
        }

        // 명시적 인텐트 + 결과받기 (ActivityResult API)
        // - reqeustCode가 존재하지 않는다
        // -> requestCode 없이도 요청자를 구분할 수 있다
        val startActivityLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                // onActivityResult에 해당하는 부분
                when (it.resultCode) {
                    RESULT_OK -> {
                        Log.d("dataa", it.data?.extras?.getString("result")!!)
                    }
                }
                // onAcitivtyResult
                // - 모든 intent가 한 곳에서 처리된다 -> 구분이 필요하다(request code)
                // ActivityResult API
                // - 각각의 intent가 처리되는 곳이 별도로 있다 -> 구분이 필요없다
            }

        (findViewById<TextView>(R.id.intent_five)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                startActivityLauncher.launch(intent)
            }
        }

        // 명시적 인텐트 + 이미지 URI 전달
        (findViewById<TextView>(R.id.intent_six)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java).apply {
                    val imageUri = Uri.parse("android.resource://" + packageName + "/drawable/" + "re_")
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_STREAM, imageUri)
                    this.setType("image/*")
                }
                startActivity(intent)
            }
        }

        // 인텐트를 이용해서 데이터 전달이 가능하다
        //  - 인텐트를 이용해서 키벨류 데이터를 전달한다
        //  - 인텐트를 이용해서 이미지를 전달한다
    }

    // 결과를 받는 부분
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //resultCode (status code)
        // - 최종결과
        // - 성공, 실패
        when (requestCode) {
            1 -> {
                when (resultCode) {
                    RESULT_OK -> {
                        val data: String? = data?.extras?.getString("result")
                        Log.d("dataa", data!!)
                    }
                }
            }
            2 -> {
                // resultCode를 사용하지 않고 바로 data를 받을 수 있다
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}