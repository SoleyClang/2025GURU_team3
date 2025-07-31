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
import com.example.habit.db.RoutineDao
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RoutineActivity : AppCompatActivity() {

    private lateinit var textRoutineSummary: TextView
    private lateinit var routines: List<Routine>
    private lateinit var adapter: RoutineAdapter
    private lateinit var dao: RoutineDao

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

        // ✅ DAO 초기화
        dao = RoutineDao(this)

        // ✅ 루틴이 없을 경우 기본 루틴 삽입
        if (dao.getAllRoutines().isEmpty()) {
            val allRoutines = ThemeType.values().flatMap { type ->
                RoutineRepository.getRoutinesForTheme(type)
            }
            allRoutines.forEach { routine ->
                dao.insertRoutine(routine)
            }
        }

        // ✅ 현재 테마에 맞는 루틴만 필터링
        routines = dao.getAllRoutines().filter { it.theme == theme }

        // ✅ RecyclerView 설정
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerRoutine)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RoutineAdapter(routines) { updatedRoutine ->
            dao.updateRoutine(updatedRoutine)
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
                dao.updateRoutine(it)
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

        // 누적 포인트 저장 및 칭호 업데이트
        UserStatsManager.addPoints(this, points)
    }
}
