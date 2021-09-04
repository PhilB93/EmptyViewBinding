package com.example.emptyviewbinding.cursor

import android.provider.BaseColumns

object DBContract {

    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "players_table"
            val COLUMN_USER_ID = "id"
            val COLUMN_NAME = "name"
            val COLUMN_AGE = "age"
            val COLUMN_SKIN = "skin"
        }
    }
}