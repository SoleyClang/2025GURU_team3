package com.example.habit.SelfDevHabitViewModel

class SelfDevHabitViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).habitDao()
    val today = LocalDate.now().toString()

    val habits: LiveData<List<HabitEntity>> =
        dao.getHabitsByDateAndTheme(today, "자기개발")

    fun checkHabit(habit: HabitEntity, checked: Boolean) {
        viewModelScope.launch {
            dao.update(habit.copy(isCompleted = checked))
        }
    }

    fun updateContent(habit: HabitEntity, newContent: String) {
        viewModelScope.launch {
            dao.update(habit.copy(content = newContent))
        }
    }

    fun insertDummyDataIfNeeded() {
        viewModelScope.launch {
            if (dao.getHabitsByDateAndTheme(today, "자기개발").value.isNullOrEmpty()) {
                val sample = listOf(
                    HabitEntity(title = "내일 할 일 리스트 작성", date = today),
                    HabitEntity(title = "1줄 일기 / 하루 기록", date = today),
                    HabitEntity(title = "뉴스 읽기 / 시사 요약", date = today),
                    HabitEntity(title = "공부 타이머 1시간", date = today)
                )
                sample.forEach { dao.insert(it) }
            }
        }
    }
}
