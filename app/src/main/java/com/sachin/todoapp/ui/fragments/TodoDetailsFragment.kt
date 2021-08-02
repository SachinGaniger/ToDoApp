package com.sachin.todoapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sachin.todoapp.R
import com.sachin.todoapp.databinding.FragmentTodoDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoDetailsFragment: Fragment(R.layout.fragment_todo_details) {

    private lateinit var binding: FragmentTodoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTodoDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }
}