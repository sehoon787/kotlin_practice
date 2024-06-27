package com.example.forest.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.forest.MainActivity
import com.example.forest.databinding.ActivityLoginBinding
import com.example.forest.data.models.LoginResponse
import com.example.forest.data.models.loginResponseSample

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private var isAutoLogin = false

    private fun loadData(){
        val sharedPref: SharedPreferences = getPreferences(Context.MODE_PRIVATE)

        val id = sharedPref.getString("KEY_ID", "")
        val pw = sharedPref.getString("KEY_PW", "")
        isAutoLogin = sharedPref.getBoolean("KEY_AUTO_LOGIN", false)
        binding.idEditText.setText(id.toString())
        binding.pwEditText.setText(pw.toString())
    }

    private fun saveData(id: String, pw: String, isLogin: Boolean){
        val sharedPref: SharedPreferences = getPreferences(Context.MODE_PRIVATE)

        val editor = sharedPref.edit()

        editor.putString("KEY_ID", id)
            .putString("KEY_PW", pw)
            .putBoolean("KEY_AUTO_LOGIN", isLogin)
            .apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)

        loadData()

        if(isAutoLogin){
            var response = loginResponseSample
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("token", response.token)
                putExtra("user", response.toUser())
            }
            startActivity(intent)
            finish()
        }
        else{
            binding.loginButton.setOnClickListener{
                if(binding.idEditText.text.isNotEmpty() && binding.pwEditText.text.isNotEmpty()){
                    val id = binding.idEditText.text.toString()
                    val pw = binding.pwEditText.text.toString()

                    // request login
                    val isLogin = true
                    var response = loginResponseSample
                    saveData(id = id, pw = pw, isLogin = isLogin)

                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("token", response.token)
                        putExtra("user", response.toUser())
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}