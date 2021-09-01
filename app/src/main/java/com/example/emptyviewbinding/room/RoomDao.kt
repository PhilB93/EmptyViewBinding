package com.example.emptyviewbinding.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.emptyviewbinding.data.NbaPlayer

@Dao
interface RoomDao {
    @Query("SELECT * " +
            "FROM players_table " +
            "WHERE CASE WHEN :filter ='color' " +
            "THEN skin = 0  " +
          "  WHEN :filter ='name' OR :filter ='age' THEN skin >= 0 END " +
            "ORDER BY" +
            " CASE WHEN :filter = 'name' THEN name  END ASC,"
            +"CASE WHEN :filter = 'age' THEN age  END ASC,"+
            "CASE WHEN :filter = 'color' THEN skin END ASC")
    fun getAllNotes(filter: String): LiveData<List<NbaPlayer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NbaPlayer)

    @Update
    suspend fun update(note: NbaPlayer)

    @Delete
    suspend fun delete(note: NbaPlayer)

    @Query("DELETE FROM players_table")
    suspend fun deleteAll()
}