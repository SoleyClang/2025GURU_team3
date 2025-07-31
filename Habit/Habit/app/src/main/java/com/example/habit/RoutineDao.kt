package com.example.habit.db

import android.content.ContentValues
import android.content.Context
import com.example.habit.Routine
import com.example.habit.ThemeType

class RoutineDao(context: Context) {
    private val dbHelper = HabitDBHelper(context)

    fun insertRoutine(routine: Routine) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("title", routine.title)
            put("description", routine.description)
            put("isChecked", if (routine.isChecked) 1 else 0)
            put("point", routine.point)
            put("theme", routine.theme.name) // ✅ theme 저장
        }
        db.insert("Routine", null, values)
        db.close()
    }

    fun updateRoutine(routine: Routine) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("isChecked", if (routine.isChecked) 1 else 0)
        }
        db.update("Routine", values, "id=?", arrayOf(routine.id.toString()))
        db.close()
    }

    fun getAllRoutines(): List<Routine> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Routine", null)
        val list = mutableListOf<Routine>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val description = cursor.getString(cursor.getColumnIndexOrThrow("description"))
            val isChecked = cursor.getInt(cursor.getColumnIndexOrThrow("isChecked")) == 1
            val point = cursor.getInt(cursor.getColumnIndexOrThrow("point"))

            val themeString = cursor.getString(cursor.getColumnIndexOrThrow("theme")) // ✅ 읽기
            val theme = ThemeType.valueOf(themeString) // ✅ enum으로 변환

            val routine = Routine(
                id = id,
                title = title,
                description = description,
                isChecked = isChecked,
                point = point,
                theme = theme
            )
            list.add(routine)
        }

        cursor.close()
        db.close()
        return list
    }

    fun getTotalPoints(): Int {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT SUM(point) FROM Routine WHERE isChecked = 1", null)
        var total = 0
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return total
    }
}
