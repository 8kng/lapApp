package com.example.lapapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.lapapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val TERM_MILLISECOND: Long = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val startbtn = binding.startBtn
        val stopbtn = binding.stopBtn
        val lapbtn = binding.lapBtn
        val timertext = binding.timer
        val recycle = binding.lapcycle

        var time = 0L
        val dataFormat = SimpleDateFormat("mm:ss:S", Locale.getDefault())
        val handler = Handler()
        val timer = object : Runnable {
            override fun run() {
                time += TERM_MILLISECOND
                timertext.text = dataFormat.format(time)
                handler.postDelayed(this, TERM_MILLISECOND)
            }
        }

        timertext.text = dataFormat.format(time)
        stopbtn.isEnabled = false

        startbtn.setOnClickListener {
            startbtn.isEnabled = false
            stopbtn.isEnabled = true

            handler.post(timer)
        }

        stopbtn.setOnClickListener {
            startbtn.isEnabled = true
            stopbtn.isEnabled = false

            handler.removeCallbacks(timer)
        }
    }
}