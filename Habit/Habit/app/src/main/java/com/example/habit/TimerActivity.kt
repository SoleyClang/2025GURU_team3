package com.example.habit

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.habit.R

class TimerActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button

    private var timer: CountDownTimer? = null
    private var isRunning = false
    private val totalTime = 60 * 60 * 1000L // 1시간

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        timerText = findViewById(R.id.textViewTimer)
        startButton = findViewById(R.id.buttonStart)
        stopButton = findViewById(R.id.buttonStop)

        startButton.setOnClickListener {
            if (!isRunning) {
                startTimer()
            }
        }

        stopButton.setOnClickListener {
            stopTimer()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                timerText.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                timerText.text = "00:00"
                isRunning = false
            }
        }.start()
        isRunning = true
    }

    private fun stopTimer() {
        timer?.cancel()
        isRunning = false
    }
}
