package com.example.mentorandroidtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mentorandroidtask.databinding.ActivityMyProfileBinding

class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUserName()
    }

    private fun setUserName(){
        val userName = getUserName()

        binding.userNameTextView.text = userName.joinToString(" "){
            it.replaceFirstChar { it.uppercase() }
        }
    }

    private fun getUserName(): List<String> {
        val textFromEmailEditText =
            intent.getStringExtra(Constants.EMAIL_TEXT)?: throw (NoSuchFieldException("missing email argument"))
        val fullName = textFromEmailEditText.substringBefore('@').split('.','_')
        return fullName
    }
}