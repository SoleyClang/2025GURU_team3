package com.example.habit

object RoutineRepository {
    fun getRoutinesForTheme(theme: ThemeType): List<Routine> {
        return when (theme) {
            ThemeType.HEALTH -> listOf(
                Routine(1, "💧 물 8잔 마시기", "하루 수분 섭취 목표 달성", false, 10),
                Routine(2, "🏃‍♀️ 30분 운동", "가벼운 운동 또는 산책", false, 20)
            )
            ThemeType.SELF_DEV -> listOf(
                Routine(3, "📰 뉴스 읽기", "하루 1개 시사 읽기", false, 10),
                Routine(4, "📚 공부 타이머 1시간", "집중 공부 1시간 이상", false, 20)
            )
            ThemeType.LIFESTYLE -> listOf(
                Routine(5, "🛏 침구 정리", "아침에 이불 정리 여부 확인", false, 10),
                Routine(6, "🍽 설거지", "식사 후 바로 설거지 하기", false, 10),
                Routine(7, "🧹 방 청소", "간단한 정리 또는 환기", false, 15),
                Routine(8, "📵 스마트폰 1시간 이하 사용", "화면 사용 시간을 줄이기 위한 실천", false, 15),
                Routine(9, "📦 1일 1정리", "하루에 한 가지라도 물건 정리하기", false, 15)
            )
        }
    }
}
