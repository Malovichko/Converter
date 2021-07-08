package com.example.networkexample.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SimpleValuteEntity(
    @PrimaryKey val numCode: String,
//    @ColumnInfo(name = "NumCode") val numCode: String,
    @ColumnInfo(name = "CharCode") val charCode: String,
    @ColumnInfo(name = "Nominal") val nominal: String,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Value") val value: String
)