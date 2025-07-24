package com.example.habit.SelfDevHabitViewModel

class TimerActivity : AppCompatActivity() {

    private var seconds = 3600
    private lateinit var tvTime: TextView
    private lateinit var timer: CountDownTimer
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTime = TextView(this).apply {
            textSize = 32f
            gravity = Gravity.CENTER
        }
        setContentView(tvTime)

        timer = object : CountDownTimer(seconds * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val min = (millisUntilFinished / 1000) / 60
                val sec = (millisUntilFinished / 1000) % 60
                tvTime.text = String.format("%02d:%02d", min, sec)
            }

            override fun onFinish() {
                tvTime.text = "공부 완료!"
                Toast.makeText(this@TimerActivity, "루틴 완료 체크!", Toast.LENGTH_LONG).show()
                // ViewModel 연동하려면 habitId 받아서 체크 처리도 가능
            }
        }

        timer.start()
        isRunning = true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRunning) timer.cancel()
    }
}