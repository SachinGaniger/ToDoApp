package com.sachin.todoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin.todoapp.db.Todo
import com.sachin.todoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    var todos:MutableLiveData<List<Todo>> = MutableLiveData()

    fun getTodos() = viewModelScope
        .launch(Dispatchers.IO) {
            if(repository.getAllTodos() == null){
                todos.postValue(mutableListOf())
            } else {
                todos.postValue(repository.getAllTodos())
            }

        }

    fun getAllTodos() : LiveData<List<Todo>>{
        return todos
    }

    fun insertTodo(todo:Todo) = viewModelScope
        .launch(Dispatchers.IO) {
            repository.insertTodo(todo)
        }

    fun deleteTodo(todo: Todo) = viewModelScope
        .launch(Dispatchers.IO) {
            repository.deleteTodo(todo)
        }

    fun updateTodo(todo: Todo) = viewModelScope
        .launch(Dispatchers.IO) {
            repository.editTodo(todo)
        }
}