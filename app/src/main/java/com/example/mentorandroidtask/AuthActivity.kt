package com.example.mentorandroidtask

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mentorandroidtask.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    private val sharedPreference by lazy { getSharedPreferences("LoginDataCheck", Context.MODE_PRIVATE) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        autoLogin()
        setListeners()
    }

    private fun setListeners() {
        listenerLoginButton()
        listenerSingUpTextView()
    }

    @SuppressLint("CommitPrefEdits")
    private fun listenerLoginButton() {
        binding.loginButton.setOnClickListener {
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

    private fun listenerSingUpTextView() {
        binding.signUpTextView.setOnClickListener {
            val singUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(singUpIntent)
            finish()
        }
    }

    private fun goToMyProfile(email: String) {
        val myProfileIntent = Intent(this, MyProfileActivity::class.java)
        myProfileIntent.putExtra(Constants.EMAIL_TEXT, email)
        startActivity(myProfileIntent)
    }


    private fun autoLogin() {
        if (sharedPreference.getString(
                Constants.sharedPref.EMAIL_KEY,
                null
            ) != null && sharedPreference.getString(Constants.sharedPref.PASS_KEY, null) != null
        ) {
            val email = sharedPreference.getString(Constants.sharedPref.EMAIL_KEY, null)
            goToMyProfile(email.toString())
        }

    }
}