package com.baruckis.webviewtest

import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)

    // PHP file only to output all the cookies: <pre><?php print_r( $_COOKIE ); ?></pre>
    val URL = "https://baruckis.com/cookies.php"

    val webView: WebView = findViewById<View>(R.id.web_view) as WebView
    webView.settings.javaScriptEnabled = true
    webView.webViewClient = WebViewClient()

    val cookieManager: CookieManager = CookieManager.getInstance()
    cookieManager.setAcceptCookie(true)
    cookieManager.removeSessionCookies{}
    cookieManager.setCookie(URL, "WebView cookie = testing cookie; Domain=.baruckis.com")
    cookieManager.flush()

    val cookie: String = cookieManager.getCookie(URL)
    Toast.makeText(this, "Cookie: $cookie", Toast.LENGTH_LONG).show()

    webView.loadUrl(URL)
  }
}