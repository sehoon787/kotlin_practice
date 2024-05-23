package com.example.test_proj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isClicked = false;
        val clickButton = findViewById<Button>(R.id.clickButton)
        val textView = findViewById<TextView>(R.id.textView)

        clickButton.setOnClickListener {
            if(!isClicked){
                textView.text = "Button clicked"
                isClicked = true

            } else{
                textView.text = "Button is not clicked"
                isClicked = false
            }
        }

        clickButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                println("test override")
            }
        })
    }
}