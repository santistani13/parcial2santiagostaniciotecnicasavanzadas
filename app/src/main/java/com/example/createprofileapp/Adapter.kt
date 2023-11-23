package com.example.createprofileapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
class Adapter(private val persona: List<Persona>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var onItemClickListener: (Persona) -> Unit

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val personPicture: ImageView = view.findViewById(R.id.personPicture)
        private val name: TextView = view.findViewById(R.id.name)
        private val age: TextView = view.findViewById(R.id.age)
        private val gender: TextView = view.findViewById(R.id.genero)
        private val phone: TextView = view.findViewById(R.id.phone)
        private val email: TextView = view.findViewById(R.id.email)


        fun bind(persona: Persona) {

            name.text = "${persona?.name?.first} ${persona?.name?.last}"
            age.text = "Edad: ${persona?.dob?.age} años"
            gender.text = "Género: ${persona?.gender}"
            phone.text = "Teléfono: ${persona?.phone}"
            email.text = "Email: ${persona?.email}"
            Glide.with(personPicture.context)
                .load(persona?.picture?.thumbnail)
                .into(personPicture)


            view.setOnClickListener {
                onItemClickListener(persona)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return persona.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quake = persona[position]
        holder.bind(quake)
    }
}
