package com.example.networkexample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.networkexample.data.database.dao.ValuteListDao
import com.example.networkexample.data.database.entities.SimpleValuteEntity

@Database(entities = [SimpleValuteEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun valuteListDao(): ValuteListDao
}