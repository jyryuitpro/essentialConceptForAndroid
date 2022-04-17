package io.jyryuitpro.essentialconceptforandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import java.lang.Exception

class Homework_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework2)

        val webview = findViewById<WebView>(R.id.webview)
        webview.setWebViewClient(object: WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                // true -> 나의 앱이 주도권을 가져오지 않는다
                // false -> 나의 앱이 주도권을 가져오겠다
                return false
            }
        })

        try {
            webview.loadUrl(intent.data!!.toString())
        } catch (exception: Exception) {

        }

        val urlPrefix = "https://"
        var finalUrl = ""

        val address = findViewById<EditText>(R.id.address)

        // addTextChangedListener 사용법
        address.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("testt", "beforeTextChanged : " + s)
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("testt", "onTextChanged : " + s)
            }

            override fun afterTextChanged(s: Editable?) {
                finalUrl = urlPrefix + s.toString()
                Log.d("testt", "afterTextChanged : " + s)
            }
        })

        // addTextChangedListener 사용법 -> 람다 형식
        address.doBeforeTextChanged { text, start, count, after ->  }
        address.doOnTextChanged { text, start, before, count ->  }
        address.doAfterTextChanged {  }

        val open = findViewById<TextView>(R.id.open)
        open.setOnClickListener {
            val url = address.text.toString()
//            Log.d("testt", url)
//            webview.loadUrl(finalUrl)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}