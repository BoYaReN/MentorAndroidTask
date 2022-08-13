package com.example.mentorandroidtask

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.example.mentorandroidtask.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    val sharedPreference by lazy { getSharedPreferences("LoginDataCheck", Context.MODE_PRIVATE) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun register(view: View){
        val mainIntent = Intent(this, MainActivity::class.java)
        if (Patterns.EMAIL_ADDRESS.matcher(binding.emailEditText.text.toString().substringBefore(" ")).matches()){
            if (binding.passEditText.text.toString().length >= 8) {
                if(binding.rememberMeCheckBox.isChecked) {
                    sharedPreference.edit().putString("email", binding.emailEditText.text.toString()).apply()
                    sharedPreference.edit().putString("password", binding.passEditText.text.toString()).apply()

                } else{
                    sharedPreference.edit().clear().apply()
                }
                mainIntent.putExtra("EMAILTEXT", binding.emailEditText.text.toString())
                startActivity(mainIntent)
            } else{
                binding.passEditText.error = getString(R.string.passError)
            }
        } else{
            binding.emailEditText.error = getString(R.string.emailError)
        }

    }

    fun signIn(view: View){
        val authIntent = Intent(this, AuthActivity::class.java)
        startActivity(authIntent)
        finish()
    }
}