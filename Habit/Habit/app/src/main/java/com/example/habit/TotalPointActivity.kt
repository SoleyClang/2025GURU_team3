package com.example.habit

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TotalPointActivity : AppCompatActivity() {

    private lateinit var textTitle: TextView
    private lateinit var textPoint: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_point)

        textTitle = findViewById(R.id.textTotalTitle)
        textPoint = findViewById(R.id.textTotalPoint)
        val btnReset = findViewById<Button>(R.id.btnResetPoints)

        updateUI()

        btnReset.setOnClickListener {
            UserStatsManager.resetPoints(this)
            updateUI()
        }
    }

    private fun updateUI() {
        val totalPoints = UserStatsManager.getTotalPoints(this)
        val title = UserStatsManager.getTitleForPoints(totalPoints)

        textTitle.text = "🏆 현재 칭호: $title"
        textPoint.text = "누적 포인트: ${totalPoints}점"
    }
}
