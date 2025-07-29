package com.example.habit

import android.app.Activity
import android.content.Intent
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoutineAdapter(
    private val routines: List<Routine>,
    private val onCheckedChanged: () -> Unit
) : RecyclerView.Adapter<RoutineAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.checkBoxRoutine)
        val textPoint: TextView = view.findViewById(R.id.textPoint)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_routine, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val routine = routines[position]

        // ✅ 공부 타이머 or 일기 루틴일 경우 밑줄 표시
        if (routine.title == "공부 타이머 1시간" || routine.title == "1줄 일기 / 하루 기록") {
            val underlined = SpannableString(routine.title)
            underlined.setSpan(UnderlineSpan(), 0, routine.title.length, 0)
            holder.checkBox.text = underlined
        } else {
            holder.checkBox.text = routine.title
        }

        holder.checkBox.isChecked = routine.isChecked
        holder.textPoint.text = "+${routine.point}포인트"

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = routine.isChecked
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            routine.isChecked = isChecked
            onCheckedChanged()
        }

        // ✅ 루틴 클릭 시 특정 액티비티 이동
        holder.checkBox.setOnClickListener {
            val context = holder.itemView.context

            when (routine.title) {
                "공부 타이머 1시간" -> {
                    val intent = Intent(context, TimerActivity::class.java)
                    context.startActivity(intent)
                }

                "1줄 일기 / 하루 기록" -> {
                    val intent = Intent(context, DiaryActivity::class.java)
                    (context as? Activity)?.startActivityForResult(intent, 101)
                }
            }
        }
    }

    override fun getItemCount(): Int = routines.size
}
