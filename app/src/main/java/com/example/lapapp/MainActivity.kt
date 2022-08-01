package com.example.lapapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.os.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
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
        var laplist = listOf<Int>()

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
            Log.d("tog", "touch")
            startbtn.isEnabled = false
            stopbtn.isEnabled = true

            startbtn.text = "start"
            time = 0
            handler.post(timer)
            laplist = listOf()
            recycle.adapter = Adapter(laplist)
            recycle.layoutManager = LinearLayoutManager(this)
        }

        stopbtn.setOnClickListener {
            startbtn.isEnabled = true
            stopbtn.isEnabled = false

            handler.removeCallbacks(timer)
            startbtn.text = "restart"
        }

        lapbtn.setOnClickListener {
            Log.d("lap", "touch")
            laplist += time.toInt()
            recycle.adapter = Adapter(laplist)
            recycle.layoutManager = LinearLayoutManager(this)
        }
    }
}