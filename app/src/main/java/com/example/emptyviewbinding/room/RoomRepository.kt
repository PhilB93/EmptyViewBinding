package com.example.emptyviewbinding.room

import androidx.lifecycle.LiveData
import com.example.emptyviewbinding.FITLER
import com.example.emptyviewbinding.RepositioryForAll
import com.example.emptyviewbinding.data.Person


class RoomRepository(private val noteDao: RoomDao):RepositioryForAll {

    override val readAllData: LiveData<List<Person>>
        get() = noteDao.getAllNotes(FITLER)


     override  suspend fun add(note: Person) {
           noteDao.insert(note)
       }

    override suspend fun update(note: Person) {
        noteDao.insert(note)

    }

    override suspend fun delete(note: Person) {
        noteDao.delete(note)

    }

    override  suspend fun deleteAll() {
        noteDao.deleteAll()

    }


}