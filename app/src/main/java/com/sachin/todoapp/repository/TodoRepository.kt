package com.sachin.todoapp.repository

import com.sachin.todoapp.db.Todo
import com.sachin.todoapp.db.TodoDao
import javax.inject.Inject


class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {

    suspend fun insertTodo(todo: Todo) = todoDao.insertTodo(todo)

    suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo)

    suspend fun editTodo(todo: Todo) = todoDao.editTodo(todo)

    fun getAllTodos() = todoDao.getAllTodos()

}