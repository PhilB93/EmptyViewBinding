package com.example.emptyviewbinding.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.emptyviewbinding.FITLER
import com.example.emptyviewbinding.data.NbaPlayer


class RoomRepository(private val noteDao: RoomDao) {

    val readAllData: LiveData<List<NbaPlayer>> = noteDao.getAllNotes(FITLER)


    suspend fun add(note: NbaPlayer) {
        noteDao.insert(note)
    }

    suspend fun update(note: NbaPlayer) {
        noteDao.insert(note)
    }

    suspend fun delete(note: NbaPlayer){
        noteDao.delete(note)
    }

    suspend fun deleteAll(){
        noteDao.deleteAll()
    }


}