package com.example.emptyviewbinding

import androidx.lifecycle.LiveData
import com.example.emptyviewbinding.data.Person

interface RepositioryForAll {
    val readAllData: LiveData<List<Person>>

    suspend fun  add(note: Person)

    suspend fun update(note: Person)

    suspend fun delete(note: Person)

}