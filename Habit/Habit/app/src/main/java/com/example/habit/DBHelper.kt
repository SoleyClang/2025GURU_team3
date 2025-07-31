package com.example.habit.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HabitDBHelper(context: Context) : SQLiteOpenHelper(context, "habit.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE Routine (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                description TEXT,
                isChecked INTEGER,
                point INTEGER,
                theme TEXT     -- ✅ 테마 컬럼 추가
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Routine")
        onCreate(db)
    }
}
