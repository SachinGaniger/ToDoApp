package com.sachin.todoapp.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachin.todoapp.R
import com.sachin.todoapp.databinding.AddTodoDialogBinding
import com.sachin.todoapp.databinding.FragmentTodoBinding
import com.sachin.todoapp.db.Todo
import com.sachin.todoapp.ui.adapters.TodoListAdapter
import com.sachin.todoapp.ui.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class TodoListFragment: Fragment(R.layout.fragment_todo) {

    private lateinit var binding: FragmentTodoBinding
    private var sdf: SimpleDateFormat?=null
    private var stf: SimpleDateFormat?=null
    private var dateString: String?=null
    private var timeString: String?=null
    private val calendar = Calendar.getInstance()
    private val viewModel : TodoViewModel by viewModels()
    private var todoAdapter: TodoListAdapter?= null
    private var todos : ArrayList<Todo>?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTodoBinding.inflate(inflater, container, false)

        sdf = SimpleDateFormat("EEE, d MMM yyyy", Locale.US)
        stf = SimpleDateFormat("hh:mm a", Locale.US)

        viewModel.getTodos();

        binding.fabAdd.setOnClickListener {
//            Toast.makeText(activity, "Fab Clicked", Toast.LENGTH_SHORT).show()
                Log.i("clicked: ", "fab clicked")
                showAddDialog()

        }

        viewModel.getAllTodos().observe(viewLifecycleOwner, androidx.lifecycle.Observer { todoList ->

            todos = todoList as ArrayList<Todo>

            if (todoList.isNullOrEmpty()){

                binding.rvTodo.visibility = GONE
                binding.tvNoList.visibility = VISIBLE

            } else{

                binding.rvTodo.visibility = VISIBLE
                binding.tvNoList.visibility = GONE
                todoAdapter = TodoListAdapter(todoList)
                binding.rvTodo.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = todoAdapter
                }

            }


        })



        return binding.root
    }

    private fun showAddDialog() {

        val dialogBinding = AddTodoDialogBinding.inflate(LayoutInflater.from(activity))

        val dialogBuilder = activity?.let {
            androidx.appcompat.app.AlertDialog.Builder(it)
                .setView(dialogBinding.root)
        }

        val alertDialog = dialogBuilder?.show()

        dialogBinding.apply {
            etDate.setOnClickListener {
                showDatePicker(dialogBinding)

            }

            etTime.setOnClickListener {
                showTimePicker(dialogBinding)
            }

            ivClose.setOnClickListener {
                alertDialog?.dismiss()
            }

            btSubmit.setOnClickListener {
                val title = etTitle.text.toString()
                val date = etDate.text.toString()
                val time = etTime.text.toString()

                viewModel.insertTodo(Todo(title, date, time))
                todoAdapter?.updateList(todos)
                alertDialog?.dismiss()

            }

        }
    }

    private fun showTimePicker(dialogBinding: AddTodoDialogBinding) {

        val timePickerDialog = activity?.let {
            TimePickerDialog(it,
                TimePickerDialog.OnTimeSetListener{ _, mHour, mMinute ->
                val date = Calendar.getInstance()
                date.set(mHour, mMinute)

                timeString = stf?.format(date.time)
                dialogBinding.etTime.setText(timeString)

            }, calendar[Calendar.HOUR], calendar[Calendar.MINUTE], false)
        }

//        datePickerDialog?.getButton(BUTTON_POSITIVE)?.setTextColor(resources.getColor(R.color.purple_500))
//        datePickerDialog?.getButton(BUTTON_NEGATIVE)?.setTextColor(resources.getColor(R.color.purple_500))

        timePickerDialog!!.show()

    }

    private fun showDatePicker(dialogBinding: AddTodoDialogBinding) {

        val datePickerDialog = activity?.let {
            DatePickerDialog(
                it,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    val date = Calendar.getInstance()
                    date.set(year, monthOfYear, dayOfMonth)

                    dateString = sdf?.format(date.time)
                    dialogBinding.etDate.setText(dateString)

                },
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH]
            )
        }

//        datePickerDialog?.getButton(BUTTON_POSITIVE)?.setTextColor(resources.getColor(R.color.purple_500))
//        datePickerDialog?.getButton(BUTTON_NEGATIVE)?.setTextColor(resources.getColor(R.color.purple_500))

        datePickerDialog!!.show()
    }
}