package com.example.peoplelist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "myDb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table people (" +
                "id integer primary key autoincrement," +
                "name text," +
                "surname text," +
                "age integer);")
        val cv = ContentValues()

        for (p in PeopleData.getPeopleList()){
            cv.put("name", p.name)
            cv.put("surname", p.surname)
            cv.put("age", p.age)
            db?.insert("people", null, cv)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}