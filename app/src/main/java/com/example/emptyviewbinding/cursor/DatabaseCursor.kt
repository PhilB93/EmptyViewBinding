package com.example.emptyviewbinding.cursor

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.emptyviewbinding.data.Person
import com.example.emptyviewbinding.room.RoomDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.SQLException
import java.util.concurrent.Executors

private const val LOG_TAG = "SQLiteOpenHelper"
private const val DATABASE_NAME = "players_database"
private const val TABLE_NAME = "players_table"
private const val DATABASE_VERSION = 2
private const val CREATE_TABLE_SQL =
    "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "id	INTEGER NOT NULL," +
            "name	TEXT NOT NULL," +
            "age	INTEGER NOT NULL," +
            "skin	INTEGER NOT NULL," +
            "PRIMARY KEY(id AUTOINCREMENT)" +
            ");"

private val executor = Executors.newSingleThreadExecutor()

class DatabaseCursor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), RoomDao {
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(CREATE_TABLE_SQL)

        } catch (exception: SQLException) {
            Log.e(LOG_TAG, "SQLiteOpenHelper", exception)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d(LOG_TAG, "onUpgrade called")
    }


    private suspend fun getCarsList(sort:String, filter: String):List<Person>{
        return withContext(Dispatchers.IO) {
            val listOfCars = mutableListOf<Person>()
            val db = writableDatabase
            val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $filter ORDER BY $sort"
            val cursor = db.rawQuery(selectQuery,null)
            cursor?.let{
                if (cursor.moveToFirst()) {
                    do {
                        val id = cursor.getInt(cursor.getColumnIndex("id"))
                        val name = cursor.getString(cursor.getColumnIndex("name"))
                        val age = cursor.getInt(cursor.getColumnIndex("age"))
                        val skin = cursor.getInt(cursor.getColumnIndex("skin"))
                        listOfCars.add(Person(id, name, age, skin))
                    } while (cursor.moveToNext())
                }
            }
            cursor.close()
            listOfCars
        }
    }
    override fun getAllNotes(filter:String, sort: String): LiveData<List<Person>> {
        return liveData<List<Person>> {
            emit(getCarsList(filter, sort))
        }
    }

    override suspend fun insert(note: Person) {
        Log.d(LOG_TAG, "Cursor addCar($note)")
        val db = writableDatabase
        val values = ContentValues()
        values.put("name",note.name)
        values.put("age",note.age)
        values.put("skin",note.skin)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    override suspend fun update(note: Person) {
        Log.d(LOG_TAG, "Cursor updateCar($note)")
        val db = writableDatabase
        val values = ContentValues()
        values.put("id",note.id)
        values.put("name",note.name)
        values.put("age",note.age)
        values.put("skin",note.skin)
        db.update(TABLE_NAME, values, "id" + "=?", arrayOf(note.id.toString()))
        db.close()
    }

    override suspend fun delete(note: Person) {
        Log.d(LOG_TAG, "Cursor deleteCar($note)")
        val db = writableDatabase
        db.delete(TABLE_NAME, "id" + "=?", arrayOf(note.id.toString()))
        db.close()
    }




     fun getCar(id: Int): LiveData<Person?> {

        val carLiveData = MutableLiveData<Person>()
        val db = writableDatabase

        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE id = $id"
        val cursor = db.rawQuery(selectQuery, null)
        Log.d(LOG_TAG, "$cursor")
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val age = cursor.getInt(cursor.getColumnIndex("age"))
                    val skin = cursor.getInt(cursor.getColumnIndex("skin"))
                    val car =  Person(id, name, age, skin)
                    Log.d(LOG_TAG, "FROM GET CAR CURSOR $car")
                    carLiveData.value = car
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        Log.d(LOG_TAG, "FROM GET CAR CURSOR ${carLiveData.value}")
        return carLiveData
    }







}