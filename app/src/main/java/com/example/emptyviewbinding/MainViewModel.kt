package com.example.emptyviewbinding

import android.app.Application
import androidx.lifecycle.*
import com.example.emptyviewbinding.data.NbaPlayer
import com.example.emptyviewbinding.room.NbaRoomDatabase
import com.example.emptyviewbinding.room.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){


    val readAllData: LiveData<List<NbaPlayer>>
    private val repository: RoomRepository

    init {

        val noteDao = NbaRoomDatabase.getDatabase(
            application
        ).noteDao()
        repository = RoomRepository(noteDao)
        readAllData = repository.readAllData


    }

    fun insert(note: NbaPlayer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(note)
        }
    }
    fun update(note: NbaPlayer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(note)
        }
    }

    fun delete(note: NbaPlayer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}