package com.example.emptyviewbinding.room

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.emptyviewbinding.data.NbaPlayer

@Database(entities = [NbaPlayer::class], version = 2, exportSchema = false)
abstract class NbaRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): RoomDao

    companion object {
        @Volatile
        private var INSTANCE: NbaRoomDatabase? = null

        fun getDatabase(context: Context): NbaRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NbaRoomDatabase::class.java,
                    "players_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}