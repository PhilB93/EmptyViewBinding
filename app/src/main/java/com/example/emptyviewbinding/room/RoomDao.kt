package com.example.emptyviewbinding.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.emptyviewbinding.data.NbaPlayer

@Dao
interface RoomDao {
    @Query("SELECT * FROM players_table ORDER BY CASE WHEN :filter = 'name' THEN name  END ASC,"
            +"CASE WHEN :filter = 'age' THEN age  END ASC,"+
            "CASE WHEN :filter = 'is' THEN skin END ASC")
    fun getAllNotes(filter: String): LiveData<List<NbaPlayer>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: NbaPlayer)

    @Delete
    suspend fun delete(note: NbaPlayer)

    @Query("DELETE FROM players_table")
    suspend fun deleteAllNotes()
}