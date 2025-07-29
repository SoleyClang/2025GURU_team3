package com.example.habit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RoutineActivity : AppCompatActivity() {

    private lateinit var textRoutineSummary: TextView
//    private lateinit var textUserTitle: TextView // ✅ 칭호 텍스트뷰
    private lateinit var routines: List<Routine>
    private lateinit var adapter: RoutineAdapter

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine)

        val themeName = intent.getStringExtra("theme")
        val theme = try {
            ThemeType.valueOf(themeName ?: "")
        } catch (e: Exception) {
            e.printStackTrace()
            finish()
            return
        }

        val date = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val formattedDate = date.format(formatter)

        val themeKorean = when (theme) {
            ThemeType.HEALTH -> "건강"
            ThemeType.SELF_DEV -> "자기개발"
            ThemeType.LIFESTYLE -> "생활습관"
        }

        val titleTextView = findViewById<TextView>(R.id.textTitle)
        titleTextView.text = "$formattedDate | 오늘의 루틴_$themeKorean"

        textRoutineSummary = findViewById(R.id.textRoutineSummary)
//        textUserTitle = findViewById(R.id.textUserTitle) // ✅ XML에서 TextView 추가 필요

        routines = RoutineRepository.getRoutinesForTheme(theme)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerRoutine)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RoutineAdapter(routines) {
            updateSummary()
        }
        recyclerView.adapter = adapter

        updateSummary()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val content = data?.getStringExtra("diaryContent") ?: return
            val diaryRoutine = routines.find { it.title == "1줄 일기 / 하루 기록" }
            diaryRoutine?.let {
                it.isChecked = true
                RoutineHistory.save(this, it.title, content)
                adapter.notifyDataSetChanged()
                updateSummary()
            }
        }
    }

    private fun updateSummary() {
        val total = routines.size
        val done = routines.count { it.isChecked }
        val points = routines.filter { it.isChecked }.sumOf { it.point }

        textRoutineSummary.text = "오늘 $done/$total 완료 | +$points 포인트"

        // ✅ 누적 포인트 저장 및 칭호 업데이트
        UserStatsManager.addPoints(this, points)
//        val totalPoints = UserStatsManager.getTotalPoints(this)
//        val title = UserStatsManager.getTitleForPoints(totalPoints)
//        textUserTitle.text = "🏆 칭호: $title"
    }
}
