package com.example.mentorandroidtask

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.example.mentorandroidtask.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    val sharedPreference by lazy { getSharedPreferences("LoginDataCheck", Context.MODE_PRIVATE) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginCheckBoxData()
    }


    fun login(view: View){
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

    fun loginCheckBoxData(){
        val mainIntent = Intent(this, MainActivity::class.java)
        if(sharedPreference.getString("email", null) != null && sharedPreference.getString("password", null) != null){
            mainIntent.putExtra("EMAILTEXT", sharedPreference.getString("email", null))
            startActivity(mainIntent)
        }

    }

    fun signUp(view: View){
        val singUpIntent = Intent(this, SignUpActivity::class.java)
        startActivity(singUpIntent)
        finish()
    }
}