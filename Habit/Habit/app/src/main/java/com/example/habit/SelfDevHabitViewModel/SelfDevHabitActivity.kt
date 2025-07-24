package com.example.habit.SelfDevHabitViewModel

class SelfDevHabitActivity : AppCompatActivity() {

    private lateinit var viewModel: SelfDevHabitViewModel
    private lateinit var adapter: SelfDevHabitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selfdev)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerHabits)
        val summary = findViewById<TextView>(R.id.tvSummary)

        viewModel = ViewModelProvider(this)[SelfDevHabitViewModel::class.java]
        viewModel.insertDummyDataIfNeeded()

        viewModel.habits.observe(this) { habits ->
            summary.text = "완료: ${habits.count { it.isCompleted }} / ${habits.size}"
            adapter = SelfDevHabitAdapter(habits,
                onCheck = { habit, checked -> viewModel.checkHabit(habit, checked) },
                onWrite = { habit -> showInputDialog(habit) },
                onStartTimer = { habit -> startTimer(habit) }
            )
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun showInputDialog(habit: HabitEntity) {
        val input = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("${habit.title}")
            .setMessage("내용을 입력하세요")
            .setView(input)
            .setPositiveButton("저장") { _, _ ->
                viewModel.updateContent(habit, input.text.toString())
            }.setNegativeButton("취소", null)
            .show()
    }

    private fun startTimer(habit: HabitEntity) {
        val intent = Intent(this, TimerActivity::class.java)
        intent.putExtra("habitId", habit.id)
        startActivity(intent)
    }
}