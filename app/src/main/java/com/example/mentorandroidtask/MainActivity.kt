package com.example.mentorandroidtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mentorandroidtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object{
        val emailText = "EMAILTEXT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserName()
    }

    fun getUserName(){
        val textFromEmailEditText =
            intent.getStringExtra(emailText)?: throw (NoSuchFieldException("missing email argument"))
        val fullName = textFromEmailEditText.substringBefore('@').split('.')

        binding.userNameTextView.text = fullName.joinToString(" "){
            it.replaceFirstChar { it.uppercase() }
        }
    }
}