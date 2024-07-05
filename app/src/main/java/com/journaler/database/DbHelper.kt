package com.journaler.database

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.journaler.Journaler


class DbHelper(private val dbName: String, val version: Int) : SQLiteOpenHelper(Journaler.ctx, dbName, null, version) {

    companion object {
       const val ID: String = "_id"
       const val TABLE_TODOS = "todos"
       const val TABLE_NOTES = "notes"
       const val COLUMN_TITLE: String = "title"
       const val COLUMN_MESSAGE: String = "message"
       const val COLUMN_SCHEDULED: String = "scheduled"
       const val COLUMN_LOCATION_LATITUDE: String = "latitude"
       const val COLUMN_LOCATION_LONGITUDE: String = "longitude"
    }

    private val tag = "DbHelper"

    private val createTableNotes =  """
                                    CREATE TABLE if not exists $TABLE_NOTES
                                    (
                                        $ID integer PRIMARY KEY autoincrement,
                                        $COLUMN_TITLE text,
                                        $COLUMN_MESSAGE text,
                                        $COLUMN_LOCATION_LATITUDE real,
                                        $COLUMN_LOCATION_LONGITUDE real
                                    )
                                    """

    private val createTableTodos =  """
                                    CREATE TABLE if not exists $TABLE_TODOS
                                    (
                                        $ID integer PRIMARY KEY autoincrement,
                                        $COLUMN_TITLE text,
                                        $COLUMN_MESSAGE text,
                                        $COLUMN_SCHEDULED integer,
                                        $COLUMN_LOCATION_LATITUDE real,
                                        $COLUMN_LOCATION_LONGITUDE real
                                    )
                                    """

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(tag, "Database [ CREATING ]")
        db.execSQL(createTableNotes)
        db.execSQL(createTableTodos)
        Log.d(tag, "Database [ CREATED ]")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Ignore for now.
    }

}