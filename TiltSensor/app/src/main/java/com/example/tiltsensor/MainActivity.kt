package com.example.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var tiltView: TiltView

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onSensorChanged(event: SensorEvent?) {
//        event.values[0] : x축, 위쪽은 양수 아래쪽은 음수
//        event.values[1] : y축, 오른쪽은 양수 왼쪽은 음수
//        event.values[2] : 미사용
        event?.let {
            Log.d("MainActivity", "onSensorChanged\n" +
                    "x : ${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")
        }

        if (event != null) {
            tiltView.onSensorEvent(event)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        print("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//      화면 꺼지지 않게 하기
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//      화면 가로 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)

        tiltView = TiltView(this)
        setContentView(tiltView)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
