package com.example.networkexample.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.networkexample.data.database.entities.SimpleValuteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ValuteListDao {

    @Query("SELECT * FROM SimpleValuteEntity")
    fun getAll(): Single<List<SimpleValuteEntity>>

    @Query("DELETE FROM SimpleValuteEntity")
    fun clearAll(): Completable

    @Insert
    fun insertAll(valutes: List<SimpleValuteEntity>): Completable
}