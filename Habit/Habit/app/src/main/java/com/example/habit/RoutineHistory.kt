package com.example.habit

import android.content.Context
import android.content.SharedPreferences

object RoutineHistory {
    private const val PREF_NAME = "RoutineHistory"
    private const val KEY_PREFIX = "diary_"

    fun save(context: Context, routineTitle: String, content: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_PREFIX + routineTitle, content).apply()
    }

    fun load(context: Context, routineTitle: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_PREFIX + routineTitle, null)
    }
}
