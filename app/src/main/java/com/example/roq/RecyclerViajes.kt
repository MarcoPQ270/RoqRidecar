package com.example.roq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roq.adapters.AdapterRViaje
import com.example.roq.dataclass.Estudiantes
import com.example.roq.dataclass.ModelCviaje

class RecyclerViajes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_viajes)

        val lista= listOf<ModelCviaje>(
            ModelCviaje("Celaya","13:15","Daniel","16980590"),
            ModelCviaje("Comonfort","10:00","Marco","16980591")
        )
        val recycler = findViewById<RecyclerView>(R.id.recyclerest)
        val adapter=AdapterRViaje(this)
        recycler.adapter=adapter

        //SELECCCIONAMOS CMO QUEREMOS QUE SE MUESTRE LA CARD DEL RECYCKERVIEW
        recycler.layoutManager = LinearLayoutManager(this)
        //recycler.layoutManager= GridLayoutManager(this,2)
        adapter.setDataToList(lista)
    }
}
