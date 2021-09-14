package com.example.emptyviewbinding.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.emptyviewbinding.data.Person

@Dao
interface RoomDao {
    @Query(
        "SELECT * FROM players_table WHERE  CASE WHEN :filter ='id NOT NULL' THEN id NOT NULL WHEN :filter ='age < 40' THEN age<40 WHEN :filter ='age > 39' THEN age>39 END  ORDER BY CASE WHEN :sort ='name' THEN name WHEN :sort ='age' THEN age  END ASC")
    fun getAllNotes(
        filter: String,
        sort: String
    ): LiveData<List<Person>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Person)

    @Update
    suspend fun update(note: Person)

    @Delete
    suspend fun delete(note: Person)


}