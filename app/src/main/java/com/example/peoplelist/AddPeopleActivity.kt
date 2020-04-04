package com.example.peoplelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_people.*
import kotlinx.android.synthetic.main.activity_main.*

class AddPeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_people)

        var people: People
        val data = intent.extras
        if (data != null){
            people = data.getParcelable("people")!!
            initActivity(people)
        }
    }

    private fun initActivity(people: People){
        add_people_name_et.setText(people.name)
        add_people_surname_et.setText(people.surname)
        add_people_age_et.setText(people.age.toString())
    }
}
