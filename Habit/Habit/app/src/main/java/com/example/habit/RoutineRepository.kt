package com.example.habit

object RoutineRepository {
    fun getRoutinesForTheme(theme: ThemeType): List<Routine> {
        return when (theme) {
            ThemeType.HEALTH -> listOf(
                Routine(1, "물 8잔 마시기", "하루 수분 섭취 목표 달성", false, 10, ThemeType.HEALTH),
                Routine(2, "30분 운동", "가벼운 운동 또는 산책", false, 20, ThemeType.HEALTH),
                Routine(10, "수면 8시간 이상", "미리 설정한 수면시간 내외로 수면", false, 20, ThemeType.HEALTH),
                Routine(11, "5000걸음 이상 걷기", "미리 설정한 걸음 수 이상 걷기", false, 15, ThemeType.HEALTH),
                Routine(12, "단백질 챙기기", "단백질이 들어간 음식 섭취", false, 10, ThemeType.HEALTH),
                Routine(13, "영양제 챙기기", "영양제 섭취", false, 10, ThemeType.HEALTH)
            )

            ThemeType.SELF_DEV -> listOf(
                Routine(14, "내일 할 일 리스트 작성", "하루 마무리 전 내일 계획 세우기", false, 10, ThemeType.SELF_DEV),
                Routine(15, "1줄 일기 / 하루 기록", "감정, 목표, 회고 등 짧은 기록", false, 10, ThemeType.SELF_DEV),
                Routine(16, "뉴스 읽기 / 시사 요약", "오늘의 이슈 읽고 키워드 정리", false, 10, ThemeType.SELF_DEV),
                Routine(17, "공부 타이머 1시간", "타이머로 학습 시간 측정", false, 20, ThemeType.SELF_DEV)
            )

            ThemeType.LIFESTYLE -> listOf(
                Routine(5, "침구 정리", "아침에 이불 정리 여부 확인", false, 10, ThemeType.LIFESTYLE),
                Routine(6, "설거지", "식사 후 바로 설거지 하기", false, 10, ThemeType.LIFESTYLE),
                Routine(7, "방 청소", "간단한 정리 또는 환기", false, 15, ThemeType.LIFESTYLE),
                Routine(8, "스마트폰 1시간 이하 사용", "화면 사용 시간을 줄이기 위한 실천", false, 15, ThemeType.LIFESTYLE),
                Routine(9, "1일 1정리", "하루에 한 가지라도 물건 정리하기", false, 15, ThemeType.LIFESTYLE)
            )
        }
    }
}
