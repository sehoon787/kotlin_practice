package com.example.flashlight

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flashlight.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private fun switch(state: Boolean){
        startService(Intent(this, TorchService::class.java).apply {
            action = if(state) {
                "on"
            } else {
                "off"
            }
            binding.flashSwitch.text = "플래시 ${action?.replaceFirstChar(Char::uppercase)}"
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val torch = Torch(this)

        binding.flashSwitch.setOnCheckedChangeListener { _, isChecked ->
            switch(isChecked)

//            if(isChecked){
//                torch.flashOn()
//            }
//            else{
//                torch.flashOff()
//            }
        }
    }
}