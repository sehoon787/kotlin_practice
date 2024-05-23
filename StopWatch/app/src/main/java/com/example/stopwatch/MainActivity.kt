package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.stopwatch.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0
    private var min = 0
    private var sec = 0
    private var milli = 0

    private var timerTask: Timer ?= null
    private var isRunning = false
    private var lap = 0

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private fun start(){
        binding.fab.setImageResource(R.drawable.baseline_pause_circle_24)
        timerTask = timer(period = 10){
            time++
            min = time / 100 / 60
            sec = time / 100 % 60
            milli = time % 100
            runOnUiThread {
                binding.minTextView.text = "$min"
                binding.secTextView.text = if(9<sec) "$sec" else "0$sec"
                binding.milliTextView.text = "$milli"
            }
        }
    }

    private fun pause(){
        binding.fab.setImageResource(R.drawable.baseline_play_circle_24)
        timerTask?.cancel()
    }

    private fun reset(){
        isRunning = false;
        binding.fab.setImageResource(R.drawable.baseline_play_circle_24)
        timerTask?.cancel()

        time = 0
        binding.minTextView.text = "0"
        binding.secTextView.text = "00"
        binding.milliTextView.text = "00"

        binding.lapLayout.removeAllViews()
        lap = 0
    }

    private fun recordLapTime(){
        val textView = TextView(this)
        textView.text = "${this.lap+1} LAP : ${this.min}:${this.sec}.${this.milli}"

        binding.lapLayout.addView(textView, lap)
        lap++
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fab.setOnClickListener{
            isRunning = !isRunning

            if(isRunning){ start() }
            else{ pause() }
        }

        binding.resetFab.setOnClickListener {
            reset()
        }

        binding.lapButton.setOnClickListener {
            recordLapTime()
        }
    }
}