package io.jyryuitpro.essentialconceptforandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ActivityOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        (findViewById<TextView>(R.id.one)).setOnClickListener {
            startActivity(Intent(this@ActivityOne, ActivityOne::class.java))
        }

        (findViewById<TextView>(R.id.two)).setOnClickListener {
//            val intent = Intent(this@ActivityOne, ActivityTwo::class.java)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
//            startActivity(intent)
            startActivity(Intent(this@ActivityOne, ActivityTwo::class.java))
        }

        (findViewById<TextView>(R.id.three)).setOnClickListener {
            startActivity(Intent(this@ActivityOne, ActivityThree::class.java))
        }
    }
}