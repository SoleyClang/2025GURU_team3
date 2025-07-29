package com.example.habit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DiaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val editText = findViewById<EditText>(R.id.editTextDiary)
        val btnSave = findViewById<Button>(R.id.btnSaveDiary)

        btnSave.setOnClickListener {
            val content = editText.text.toString()
            if (content.isNotBlank()) {
                val resultIntent = Intent().apply {
                    putExtra("diaryContent", content)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                Toast.makeText(this, "일기 저장됨", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}