package com.example.bauweek5

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bauweek5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Viewbinding Setcontenview to binding.root
        val binding = ActivityMainBinding.inflate(layoutInflater)

        //Glide dependency must be added.
        binding.btnUploadImage.setOnClickListener {
            //Glide asks for a context and this keyword indicates the activity
            Glide.with(this)
                .load("https://i.picsum.photos/id/14/2500/1667.jpg?hmac=ssQyTcZRRumHXVbQAVlXTx-MGBxm6NHWD3SryQ48G-o")
                .centerCrop()
                .into(binding.ivProfilePic)
        }

        binding.btnUpdate.setOnClickListener {
            //Check the email adress. Extension function first.
            if (binding.etProfileEmail.text.toString().isValidEmail()) {

                //Check password length
                if (binding.etProfilePassword.text.length >= 6) {

                    //Check agreement checkbox
                    if (binding.agreementCheckBox.isChecked) {

                        //If everything is correct, get the fullname in edittext and set it to the textview.
                        binding.tvProfileFullName.text = binding.etProfileFullName.text

                        //Set the gender coming from the radio button to the textview.
                        if (binding.rbMale.isChecked) {
                            binding.tvProfileGender.text = "Male "
                        } else {
                            binding.tvProfileGender.text = "Female  "
                        }

                        Toast.makeText(this, "Profile information is updated", Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(this, "Please check the agreement", Toast.LENGTH_LONG).show()
                    }

                } else {
                    Toast.makeText(
                        this,
                        "Password should contain more than 6 characters.",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } else {
                Toast.makeText(this, "Please check email address.", Toast.LENGTH_LONG).show()
            }
        }

        binding.etProfilePassword.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(string: CharSequence?, start: Int, count: Int, after: Int) {

                when(string?.length){
                    5 -> {
                        binding.tvPasswordSecurityLevel.visibility = View.GONE
                    }
                    6 -> {
                        binding.tvPasswordSecurityLevel.visibility = View.VISIBLE
                        binding.tvPasswordSecurityLevel.text = "Security Level: Low"
                        binding.tvPasswordSecurityLevel.setTextColor(Color.RED)
                    }
                    7 -> {
                        binding.tvPasswordSecurityLevel.text = "Security Level: Medium"
                        binding.tvPasswordSecurityLevel.setTextColor(Color.YELLOW)
                    }
                    8 -> {
                        binding.tvPasswordSecurityLevel.text = "Security Level: High"
                        binding.tvPasswordSecurityLevel.setTextColor(Color.GREEN)
                    }
                    else -> {
                        binding.tvPasswordSecurityLevel.text = "Security Level: High"
                        binding.tvPasswordSecurityLevel.setTextColor(Color.GREEN)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        //Setting the contentView to binding.root
        setContentView(binding.root)
    }

    //Extension function to check if the email is in the right format
    private fun String.isValidEmail() =
        !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
