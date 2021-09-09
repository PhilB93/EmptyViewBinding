package com.example.emptyviewbinding.update

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.emptyviewbinding.REPOSITORY
import com.example.emptyviewbinding.TYPE_DATABASE
import com.example.emptyviewbinding.TYPE_ROOM
import com.example.emptyviewbinding.add.DatabaseCursor
import com.example.emptyviewbinding.data.NbaPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application

    val db = DatabaseCursor(mContext)
    fun update(note: NbaPlayer) =
        viewModelScope.launch(Dispatchers.IO) {
            when (TYPE_DATABASE) {
                TYPE_ROOM -> {
                    REPOSITORY.update(note)
                }
                else -> {
                    db.update(note)
                }
            }
        }


    fun delete(note: NbaPlayer) =
        viewModelScope.launch(Dispatchers.IO) {
            when (TYPE_DATABASE) {
                TYPE_ROOM -> {
                    REPOSITORY.delete(note)
                }
                else -> {
                    db.delete(note)
                }

            }
        }
}
