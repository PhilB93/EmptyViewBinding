package com.example.emptyviewbinding.room

import androidx.lifecycle.LiveData
import com.example.emptyviewbinding.util.FILTER
import com.example.emptyviewbinding.util.SORT
import com.example.emptyviewbinding.repository.RepositioryForAll
import com.example.emptyviewbinding.data.Person


class RoomRepository(private val noteDao: RoomDao): RepositioryForAll {

    override val readAllData: LiveData<List<Person>>
        get() = noteDao.getAllNotes(FILTER, SORT)


     override  suspend fun add(note: Person) {
           noteDao.insert(note)
       }

    override suspend fun update(note: Person) {
        noteDao.insert(note)

    }

    override suspend fun delete(note: Person) {
        noteDao.delete(note)

    }



}