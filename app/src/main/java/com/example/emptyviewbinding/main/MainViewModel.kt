package com.example.emptyviewbinding.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.emptyviewbinding.cursor.DatabaseCursor

import com.example.emptyviewbinding.data.Person
import com.example.emptyviewbinding.room.NbaRoomDatabase
import com.example.emptyviewbinding.room.RoomRepository
import com.example.emptyviewbinding.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application
    val readAllData: LiveData<List<Person>>
    private val db = DatabaseCursor(mContext)

    init {
        when (TYPE_DATABASE) {
            TYPE_ROOM -> {
                val noteDao = NbaRoomDatabase.getDatabase(mContext).noteDao()
                REPOSITORY = RoomRepository(noteDao)
                readAllData = REPOSITORY.readAllData
            }
            else ->
                readAllData = db.getAllNotes(SORT, FILTER)
        }
        Log.i("123", "INIT SORT $SORT AND FILTER $FILTER")

    }
}