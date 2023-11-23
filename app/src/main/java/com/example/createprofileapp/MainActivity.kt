package com.example.createprofileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var ListPersona = mutableListOf<Persona>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclePerson)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(ListPersona)
        recyclerView.adapter = adapter
        val btnReload: Button = findViewById(R.id.btnReload)
        btnReload.setOnClickListener {
            ListPersona.clear()
            getPersona()
        }
    }

    private fun getPersona() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getPersona(persona)
            val response = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    response?.results?.let { results ->
                        ListPersona.addAll(results)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }


    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = "https://randomuser.me/"
        const val persona = "api/"
    }
}