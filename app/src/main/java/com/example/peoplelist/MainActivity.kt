package com.example.peoplelist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peoplelist.PeopleAdapter.OnListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var peopleAdapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peopleAdapter = PeopleAdapter(object : OnListener {
            override fun onClick(people: People) {
                intent = Intent(this@MainActivity, UpdatePeopleActivity::class.java)
                intent.putExtra("people", people)
                startActivityForResult(intent, 202)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = peopleAdapter
        peopleAdapter.updatePeople(App.instance!!.getAllPeople())
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.add_people_btn -> {
                intent = Intent(this@MainActivity, AddPeopleActivity::class.java)
                startActivityForResult(intent, 101)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                101, 202 -> peopleAdapter.updatePeople(App.instance!!.getAllPeople())
            }
        }
    }
}
