package com.sachin.todoapp.db

import androidx.room.*

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Update
    suspend fun editTodo(todo: Todo)

    @Query("SELECT * FROM todo_table")
    fun getAllTodos() : List<Todo>

}