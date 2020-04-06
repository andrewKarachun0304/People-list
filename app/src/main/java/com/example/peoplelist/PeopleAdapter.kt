package com.example.peoplelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeopleAdapter(val onListener: OnListener) : RecyclerView.Adapter<PeopleAdapter.PeopleVH>() {
    private var peopleList: List<People> = ArrayList()

    fun updatePeople(peopleList: List<People>) {
        this.peopleList = peopleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.people_item_view, parent, false)
        return PeopleVH(view)
    }

    override fun getItemCount() = peopleList.size

    override fun onBindViewHolder(holder: PeopleVH, position: Int) {
        holder.bind(peopleList[position])
    }

    inner class PeopleVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nameTv: TextView
        private var surnameTv: TextView
        private var ageTv: TextView

        init {
            with(itemView) {
                nameTv = findViewById(R.id.people_name_tv)
                surnameTv = findViewById(R.id.people_surname_tv)
                ageTv = findViewById(R.id.people_age_tv)

                setOnClickListener {
                        onListener.onClick(peopleList[layoutPosition])
                }
            }
        }

        fun bind(people: People) {
            nameTv.text = people.name
            surnameTv.text = people.surname
            ageTv.text = people.age.toString()
        }
    }

    interface OnListener {
        fun onClick(people: People)
    }
}