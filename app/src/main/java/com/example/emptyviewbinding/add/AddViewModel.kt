package com.example.emptyviewbinding.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptyviewbinding.SORT
import com.example.emptyviewbinding.REPOSITORY
import com.example.emptyviewbinding.TYPE_DATABASE
import com.example.emptyviewbinding.TYPE_ROOM
import com.example.emptyviewbinding.cursor.DatabaseCursor
import com.example.emptyviewbinding.data.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application
    val db = DatabaseCursor(mContext)
    fun insert(note: Person) =
        viewModelScope.launch(Dispatchers.IO) {
            when (TYPE_DATABASE) {
                TYPE_ROOM -> REPOSITORY.add(note)
                else -> db.insert(note)
            }

        }
}
