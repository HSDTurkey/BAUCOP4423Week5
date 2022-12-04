package com.example.bauweek6

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.bauweek6.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    //Create the binding variable
    private lateinit var binding: ActivityWebViewBinding

    private var instagramLink: String? = null
    private var twitterLink: String? = null
    private var socialMediaLink: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize it and setContentView to binding.root
        binding = ActivityWebViewBinding.inflate(layoutInflater)

        //You have to write the same keyword that you indicated or sent
        instagramLink = intent.getStringExtra("Instagram").toString()
        twitterLink = intent.getStringExtra("Twitter").toString()

        //Social media link is actually initialized with these 2 links.
        socialMediaLink = when {
            instagramLink != "null" -> {
                instagramLink
            }
            else -> {
                twitterLink
            }
        }

        //Inside of onCreate method should be empty as possible so you have to use methods and divide this code block.

        webViewSetup(socialMediaLink)

        setContentView(binding.root)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup(url: String?) {
        //apply here means: if its available, run the codes inside the brackets.
        binding.webView.apply {
            webViewClient = WebViewClient()
            //Inside of apply, we can reach its methods without actually using with dot
            loadUrl(url!!)

            //In webpage that is inside in WebView, we have to activate it using this method. Using javascript, we have to be careful
            //And it also gives us a warning
            settings.javaScriptEnabled = true
        }
    }

    //Lets say in instagram, you clicked on a picture and when you press back, you want to go back to the main page right?
    //If you do not write these codes, it will go back to the application instead of the main page, so we need to handle it
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) binding.webView.goBack() else super.onBackPressed()

    }
}