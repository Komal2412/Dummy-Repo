package com.example.dogs.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DogBreed::class), version = 1)
abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao() : DogDao

    // creates static functions and variables
    companion object {

        @Volatile private var instance: DogDatabase? = null

        private val LOCK = Any()


        // will return instance of database if already created
        // if not will synchronize all threads and build an instance and then return it
        operator fun invoke(context: Context) = instance ?:  synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DogDatabase::class.java,
            "dogdatabase"
        ).build()

    }

}