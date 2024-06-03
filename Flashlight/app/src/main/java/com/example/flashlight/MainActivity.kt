package com.example.flashlight

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val torch = Torch(this)

        binding.flashSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                binding.flashSwitch.text = "플래시 On"
                torch.flashOn()
            }
            else{
                binding.flashSwitch.text = "플래시 Off"
                torch.flashOff()
            }
        }
    }
}