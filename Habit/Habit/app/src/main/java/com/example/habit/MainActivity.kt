package com.example.habit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnHealth).setOnClickListener {
            openRoutineScreen(ThemeType.HEALTH)
        }
        findViewById<Button>(R.id.btnSelfDev).setOnClickListener {
            openRoutineScreen(ThemeType.SELF_DEV)
        }
        findViewById<Button>(R.id.btnLifestyle).setOnClickListener {
            openRoutineScreen(ThemeType.LIFESTYLE)
        }


    }

    private fun openRoutineScreen(theme: ThemeType) {
        val intent = Intent(this, RoutineActivity::class.java)
        intent.putExtra("theme", theme.name)  // 반드시 name 사용!
        startActivity(intent)
    }
}
