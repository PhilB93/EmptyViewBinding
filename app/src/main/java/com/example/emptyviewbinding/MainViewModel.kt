package com.example.emptyviewbinding

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.emptyviewbinding.add.DatabaseCursor

import com.example.emptyviewbinding.data.NbaPlayer
import com.example.emptyviewbinding.room.NbaRoomDatabase
import com.example.emptyviewbinding.room.RoomRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
private val mContext = application
    val readAllData: LiveData<List<NbaPlayer>>
val db = DatabaseCursor(mContext)
    init {
        when (TYPE_DATABASE) {
            TYPE_ROOM -> {
                val noteDao = NbaRoomDatabase.getDatabase(mContext).noteDao()
                REPOSITORY = RoomRepository(noteDao)
                readAllData = REPOSITORY.readAllData
            }
            else ->

                readAllData = db.getAllNotes(FITLER)

            }
        Log.i("123", "INIT $FITLER")
            /*TYPE_CURSOR ->
            {

            }*/
        }
    }

  //  }


/*
fun add(note: NbaPlayer, onSuccess: () -> Unit) =
    viewModelScope.launch(Dispatchers.IO) {
        REPOSITORY.add(note, onSuccess)
    }

fun update(note: NbaPlayer, onSuccess: () -> Unit) =
    viewModelScope.launch(Dispatchers.IO) {
        REPOSITORY.update(note, onSuccess)
    }


fun delete(note: NbaPlayer, onSuccess: () -> Unit) =
    viewModelScope.launch(Dispatchers.IO) {
        REPOSITORY.delete(note)
    }


fun deleteAll(onSuccess: () -> Unit) =
    viewModelScope.launch(Dispatchers.IO) {
        REPOSITORY.deleteAll(onSuccess)
    }
*/
