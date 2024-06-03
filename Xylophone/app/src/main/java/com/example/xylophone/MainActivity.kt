package com.example.xylophone

import android.media.SoundPool
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.xylophone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val soundPool = SoundPool.Builder().setMaxStreams(8).build()

    private val sounds by lazy {
        listOf(
            Pair(binding.do1, R.raw.do1),
            Pair(binding.re, R.raw.re),
            Pair(binding.mi, R.raw.mi),
            Pair(binding.fa, R.raw.fa),
            Pair(binding.sol, R.raw.sol),
            Pair(binding.la, R.raw.la),
            Pair(binding.si, R.raw.si),
            Pair(binding.do2, R.raw.do2),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Manifest에서 정의함
        // requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sounds.forEach{ tune(it) }
    }

    private fun tune(pitch: Pair<TextView, Int>){
        val soundId = soundPool.load(this, pitch.second, 1)
        pitch.first.setOnClickListener {
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}