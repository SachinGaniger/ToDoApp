package com.sachin.todoapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    val title:String ?= null,
    val date:String ?= null,
    val time:String ?= null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int?= null
}
