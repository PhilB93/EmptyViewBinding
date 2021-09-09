package com.example.emptyviewbinding.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.emptyviewbinding.FITLER
import com.example.emptyviewbinding.RepositioryForAll
import com.example.emptyviewbinding.data.NbaPlayer


class RoomRepository(private val noteDao: RoomDao):RepositioryForAll {

    override val readAllData: LiveData<List<NbaPlayer>>
        get() = noteDao.getAllNotes(FITLER)


     override  suspend fun add(note: NbaPlayer) {
           noteDao.insert(note)
       }

    override suspend fun update(note: NbaPlayer) {
        noteDao.insert(note)

    }

    override suspend fun delete(note: NbaPlayer) {
        noteDao.delete(note)

    }

    override  suspend fun deleteAll() {
        noteDao.deleteAll()

    }


}