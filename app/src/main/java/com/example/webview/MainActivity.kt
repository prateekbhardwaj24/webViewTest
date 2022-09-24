package com.example.webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    var urlPlay = ""
    var count = 0
    var searchClicked = false

    @SuppressLint("WebViewApiAvailability", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view1 = findViewById<WebView>(R.id.webView)

        view1.settings.javaScriptEnabled = true
        view1.loadUrl("https://music.youtube.com/")
        view1.webViewClient = object : WebViewClient() {
            override fun onLoadResource(view: WebView, url: String) {
                super.onLoadResource(view, url)
            }
            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                println("check webview1 :- $url")
                findViewById<TextView>(R.id.text).text = url
                if (url != null) {
                    urlPlay = url
                }
                count++
                if (count==3 ) {
                    count =0
                    view?.stopLoading()
                    startActivity(Intent(this@MainActivity, MainActivity2::class.java))
                    finish()
                }
                super.doUpdateVisitedHistory(view, url, isReload)
            }
            override fun shouldOverrideUrlLoading(view: WebView, req: WebResourceRequest): Boolean {
                println("when you click on any interlink on webview that time you got url :-${req.url}")
                return super.shouldOverrideUrlLoading(view, req)
            }

        }

    }
}