package com.example.habit

import android.annotation.SuppressLint
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

        // 1. 날짜 + 테마 조합
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

        // 2. 리스트
        val routines = RoutineRepository.getRoutinesForTheme(theme)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerRoutine)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RoutineAdapter(routines)
    }
}
