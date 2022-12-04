package com.example.bauweek6

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bauweek6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Create the binding variable
    private lateinit var binding: ActivityMainBinding

    //Rating var
    private var _rating: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize it and setContentView to binding.root
        binding = ActivityMainBinding.inflate(layoutInflater)

        //RatingBar change listener
        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            binding.ratingTextView.text = "Your rate $rating"
            _rating = rating
        }

        //Send waiting value using the button
        binding.sendRatingButton.setOnClickListener {
            if (_rating != null) {
                //Set progressView's visibility to visible
                binding.progressBar.visibility = View.VISIBLE

                //Using handler to create a delay
                Handler(Looper.getMainLooper()).postDelayed({

                    //After 3 seconds, progress bar will disappear
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()

                }, 3000)
            } else {
                Toast.makeText(this, "Please select your rate", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to WebView using Instagram CardView
        binding.cardViewInstagram.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("Instagram", "https://www.instagram.com/huaweimobiletr/")
            startActivity(intent)
        }

        // Navigate to WebView using Twitter CardView
        binding.cardViewTwitter.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("Twitter", "https://twitter.com/huaweiturkiye")
            startActivity(intent)
        }


        //We will give uri for this, it will show the application if the application exits in our phone according to the uri
        binding.appGalleryImageView.setOnClickListener {

            try {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://appgallery.cloud.huawei.com/ag/n/app/C10132067")
                )
                //This is for opening a new task.
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

                //We need to surround it with try-catch if the link is not available for example.
            } catch (exception: ActivityNotFoundException) {
                Toast.makeText(this, "Huawei AppGallery not found!", Toast.LENGTH_SHORT).show()
            }

        }

        setContentView(binding.root)

    }
}