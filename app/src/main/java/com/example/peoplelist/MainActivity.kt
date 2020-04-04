package com.example.peoplelist

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peoplelist.PeopleAdapter.OnListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DBHelper(applicationContext)
        db = dbHelper.writableDatabase

        var peopleAdapter = PeopleAdapter(object : OnListener{
            override fun onClick(people: People) {
                intent = Intent(this@MainActivity, AddPeopleActivity::class.java)
                intent.putExtra("people", people)
                startActivity(intent)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = peopleAdapter
        peopleAdapter.updatePeople(getAllPeople())
    }

    fun getAllPeople(): List<People>{
        val cursor = db.query("people", null, null, null,
            null, null, null)
        var peopleList: ArrayList<People> = ArrayList()
        if (cursor.moveToFirst()){
            val nameIndex = cursor.getColumnIndex("name")
            val surnameIndex = cursor.getColumnIndex("surname")
            val ageIndex = cursor.getColumnIndex("age")

            do {
                peopleList.add(People(cursor.getString(nameIndex),
                    cursor.getString(surnameIndex), cursor.getInt(ageIndex)))
            }while (cursor.moveToNext())
        }
        cursor.close()
        return peopleList
    }

    fun onClick(view: View) {

    }
}
