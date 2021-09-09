package com.example.emptyviewbinding

import androidx.lifecycle.LiveData
import com.example.emptyviewbinding.data.NbaPlayer

interface RepositioryForAll {
    val readAllData: LiveData<List<NbaPlayer>>

    suspend fun  add(note: NbaPlayer)

    suspend fun update(note: NbaPlayer)

    suspend fun delete(note: NbaPlayer)

    suspend fun deleteAll()

}