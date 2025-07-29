package com.example.habit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 각 루틴 테마 버튼 클릭 시 해당 화면으로 이동
        findViewById<Button>(R.id.btnHealth).setOnClickListener {
            openRoutineScreen(ThemeType.HEALTH)
        }
        findViewById<Button>(R.id.btnSelfDev).setOnClickListener {
            openRoutineScreen(ThemeType.SELF_DEV)
        }
        findViewById<Button>(R.id.btnLifestyle).setOnClickListener {
            openRoutineScreen(ThemeType.LIFESTYLE)
        }

        // ✅ 누적 포인트 확인 버튼 클릭 시 TotalPointActivity로 이동
        findViewById<Button>(R.id.btnTotalPoint).setOnClickListener {
            val intent = Intent(this, TotalPointActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openRoutineScreen(theme: ThemeType) {
        val intent = Intent(this, RoutineActivity::class.java)
        intent.putExtra("theme", theme.name)  // 반드시 name 사용!
        startActivity(intent)
    }
}
