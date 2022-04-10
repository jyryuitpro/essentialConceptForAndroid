package io.jyryuitpro.essentialconceptforandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ViewControl_02 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_control02)

        val textViewOne: TextView = findViewById(R.id.textViewOne)
        val buttonOne: Button = findViewById(R.id.buttonOne)

        Log.d("testt", textViewOne.text.toString())
    }
}