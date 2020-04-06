package com.example.peoplelist

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_people.*

class AddPeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_people)
    }

    fun onClick(view: View) {
        when(view.id){
            R.id.add_new_people_btn -> {
                val name = add_people_name_et.text.toString()
                val surname = add_people_surname_et.text.toString()
                val age = add_people_age_et.text.toString().toInt()

                App.instance!!.addPeople(People(name, surname, age))

                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}
