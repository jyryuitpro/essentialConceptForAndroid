package io.jyryuitpro.essentialconceptforandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ActivityThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)

        (findViewById<TextView>(R.id.one)).setOnClickListener {
            val intent = Intent(this@ActivityThree, ActivityOne::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
//            startActivity(Intent(this@ActivityThree, ActivityOne::class.java))
        }

        (findViewById<TextView>(R.id.two)).setOnClickListener {
//            val intent = Intent(this@ActivityThree, ActivityTwo::class.java)
//            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
//            startActivity(intent)
            startActivity(Intent(this@ActivityThree, ActivityTwo::class.java))
        }

        (findViewById<TextView>(R.id.three)).setOnClickListener {
            startActivity(Intent(this@ActivityThree, ActivityThree::class.java))
        }
    }
}