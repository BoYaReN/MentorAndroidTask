package com.example.mentorandroidtask

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mentorandroidtask.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private val sharedPreference by lazy { getSharedPreferences("LoginDataCheck", Context.MODE_PRIVATE) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
    }

    private fun setListener(){
        listenerSignInTextView()
        listenerRegisterButton()
    }

    @SuppressLint("CommitPrefEdits")
    private fun listenerRegisterButton() {
        binding.registerButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val pass = binding.passEditText.text.toString()

            val emailValidation = validator.emailValidation(email)
            val passValidation = validator.passValidation(pass)

            if (!emailValidation) {
                binding.emailEditText.error = getString(R.string.emailError)
            }
            if (!passValidation) {
                binding.passEditText.error = getString(R.string.passError)
            }

            if (emailValidation && passValidation) {
                if (binding.rememberMeCheckBox.isChecked) {
                    sharedPreference.edit()
                        .putString(Constants.sharedPref.EMAIL_KEY, binding.emailEditText.text.toString()).apply()
                    sharedPreference.edit()
                        .putString(Constants.sharedPref.PASS_KEY, binding.passEditText.text.toString()).apply()
                } else {
                    sharedPreference.edit().clear().apply()
                }
                goToMyProfile(email)
            }
        }
    }

    private fun goToMyProfile(email: String) {
        val myProfileIntent = Intent(this, MyProfileActivity::class.java)
        myProfileIntent.putExtra(Constants.EMAIL_TEXT, email)
        startActivity(myProfileIntent)
    }

    private fun listenerSignInTextView(){
        binding.signInTextView.setOnClickListener{
            val authIntent = Intent(this, AuthActivity::class.java)
            startActivity(authIntent)
            finish()
        }
    }
}