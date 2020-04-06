package com.example.peoplelist

import android.app.Application
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class App: Application() {

    private val db: SQLiteDatabase by lazy { DBHelper(this).writableDatabase }

    override fun onCreate() {
        super.onCreate()
        instance = this@App
    }

    fun getAllPeople(): List<People> {
        val cursor = db.query(
            "people", null, null, null,
            null, null, null
        )
        val peopleList: ArrayList<People> = ArrayList()

        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex("name")
            val surnameIndex = cursor.getColumnIndex("surname")
            val ageIndex = cursor.getColumnIndex("age")

            do {
                peopleList.add(
                    People(
                        cursor.getString(nameIndex),
                        cursor.getString(surnameIndex),
                        cursor.getInt(ageIndex)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return peopleList
    }

    fun addPeople(people: People){
        val values = ContentValues()

        values.put("name", people.name)
        values.put("surname", people.surname)
        values.put("age", people.age)

        db.insert("people", null, values)
    }

    fun updatePeople(oldPeople: People, newPeople: People){
        val values = ContentValues()

        values.put("name", newPeople.name)
        values.put("surname", newPeople.surname)
        values.put("age", newPeople.age)

        db.update("people", values, "name = ? and surname = ? and age = ?",
            arrayOf(oldPeople.name, oldPeople.surname, oldPeople.age.toString()))
    }

    companion object{
        var instance: App? = null
    }
}