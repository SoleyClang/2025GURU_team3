package com.example.habit

import android.content.Context

object UserStatsManager {
        private const val PREF_NAME = "UserStats"
        private const val KEY_TOTAL_POINTS = "totalPoints"

        fun addPoints(context: Context, point: Int) {
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val total = prefs.getInt(KEY_TOTAL_POINTS, 0) + point
            prefs.edit().putInt(KEY_TOTAL_POINTS, total).apply()
        }

        fun getTotalPoints(context: Context): Int {
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return prefs.getInt(KEY_TOTAL_POINTS, 0)
        }

        fun getTitleForPoints(points: Int): String {
            return when {
                points >= 200 -> "✨ 루틴 마스터"
                points >= 100 -> "🔥 꾸준 루틴러"
                points >= 50 -> "🌱 초보 루틴러"
                else -> "👶 루틴 새싹"
            }
        }
    fun resetPoints(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(KEY_TOTAL_POINTS, 0).apply()
    }

}

