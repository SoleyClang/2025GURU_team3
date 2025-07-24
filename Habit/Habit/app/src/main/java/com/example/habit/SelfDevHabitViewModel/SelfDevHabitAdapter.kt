package com.example.habit.SelfDevHabitViewModel

class SelfDevHabitAdapter(
    private val habits: List<HabitEntity>,
    private val onCheck: (HabitEntity, Boolean) -> Unit,
    private val onWrite: (HabitEntity) -> Unit,
    private val onStartTimer: (HabitEntity) -> Unit
) : RecyclerView.Adapter<SelfDevHabitAdapter.HabitViewHolder>() {

    inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.cbHabit)
        val btnAction: Button = itemView.findViewById(R.id.btnAction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_selfdev_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        holder.checkBox.text = habit.title
        holder.checkBox.isChecked = habit.isCompleted
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            onCheck(habit, isChecked)
        }

        when (habit.title) {
            "1줄 일기 / 하루 기록", "내일 할 일 리스트 작성" -> {
                holder.btnAction.text = "작성"
                holder.btnAction.setOnClickListener { onWrite(habit) }
                holder.btnAction.visibility = View.VISIBLE
            }
            "공부 타이머 1시간" -> {
                holder.btnAction.text = "타이머 시작"
                holder.btnAction.setOnClickListener { onStartTimer(habit) }
                holder.btnAction.visibility = View.VISIBLE
            }
            else -> holder.btnAction.visibility = View.GONE
        }
    }

    override fun getItemCount() = habits.size
}