package com.sachin.todoapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sachin.todoapp.db.TodoDatabase
import com.sachin.todoapp.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, TodoDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideDao(database: TodoDatabase) = database.getDao()

}