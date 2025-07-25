package com.example.habit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoutineAdapter(
    private val routines: List<Routine>,
    private val onCheckedChanged: () -> Unit // 콜백 추가
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
        holder.checkBox.text = routine.title
        holder.checkBox.isChecked = routine.isChecked
        holder.textPoint.text = "+${routine.point}포인트"

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = routine.isChecked
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            routine.isChecked = isChecked
            onCheckedChanged()
        }
    }

    override fun getItemCount(): Int = routines.size
}
