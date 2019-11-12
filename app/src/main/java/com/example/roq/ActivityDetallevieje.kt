package com.example.roq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roq.adapters.AdapterRViaje
import com.example.roq.adapters.Adapterdetalleviaje
import com.example.roq.dataclass.Estdetalle
import com.example.roq.dataclass.Estudiantes

class ActivityDetallevieje : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detallevieje)

        val lista= listOf<Estdetalle>(
            Estdetalle("16980598","Marco","7","Tics")
        )

        val recycler = findViewById<RecyclerView>(R.id.recyclerdetalles)
        val adapter= Adapterdetalleviaje(this)
        recycler.adapter=adapter
        recycler.layoutManager=LinearLayoutManager(this)
        adapter.setDataToList(lista)
    }


}
