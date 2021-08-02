package com.sachin.todoapp.ui.fragments

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sachin.todoapp.R
import com.sachin.todoapp.databinding.AddTodoDialogBinding
import com.sachin.todoapp.databinding.FragmentTodoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TodoListFragment: Fragment(R.layout.fragment_todo) {

    private lateinit var binding: FragmentTodoBinding
    private var sdf: SimpleDateFormat?=null
    private var dateString: String?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTodoBinding.inflate(inflater, container, false)

        sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        binding.fabAdd.setOnClickListener {
//            Toast.makeText(activity, "Fab Clicked", Toast.LENGTH_SHORT).show()
                Log.i("clicked: ", "fab clicked")
                showAddDialog()

        }

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
                showDatePicker()

            }
            etDate.setText(dateString)
        }
    }

    private fun showDatePicker()  {

        val calendar = Calendar.getInstance()

        val datePickerDialog = activity?.let {
            DatePickerDialog(it, R.style.DialogTheme, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val date = Calendar.getInstance()
                date.set(year, monthOfYear, dayOfMonth)

                dateString = sdf?.format(date.time)

            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
        }

        datePickerDialog!!.show()
    }
}