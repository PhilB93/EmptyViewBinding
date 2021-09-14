package com.example.emptyviewbinding.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "players_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    var name: String ="",
    @ColumnInfo
    var age: Int =0,
    @ColumnInfo
    var skin: Int =0
): Serializable