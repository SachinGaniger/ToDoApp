package com.sachin.todoapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin.todoapp.databinding.SingleTodoLayoutBinding
import com.sachin.todoapp.db.Todo

class TodoListAdapter(
    private var todoList: List<Todo>
) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {
    class ViewHolder(private val binding: SingleTodoLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.apply {
                tvTitle.text = todo.title
                tvDateTime.text = todo.date +" "+ todo.time
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SingleTodoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoList[position]
        holder.bind(todo)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateList(todos: ArrayList<Todo>?) {
        todoList = todos as ArrayList<Todo>
        notifyDataSetChanged()
    }
}