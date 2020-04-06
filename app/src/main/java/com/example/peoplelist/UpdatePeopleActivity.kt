package com.example.peoplelist

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_update_people.*

class UpdatePeopleActivity : AppCompatActivity() {

    private lateinit var oldPeople: People

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_people)

        val data = intent.extras
        if (data != null){
            oldPeople = data.getParcelable("people")!!
            initActivity(oldPeople)
        }
    }

    private fun initActivity(people: People){
        update_people_name_et.setText(people.name)
        update_people_surname_et.setText(people.surname)
        update_people_age_et.setText(people.age.toString())
    }

    fun onClick(view: View) {
        when(view.id){
            R.id.update_people_btn -> {
                val name = update_people_name_et.text.toString()
                val surname = update_people_surname_et.text.toString()
                val age = update_people_age_et.text.toString().toInt()

                App.instance!!.updatePeople(oldPeople, People(name, surname, age))

                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}
