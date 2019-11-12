package com.example.roq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roq.adapters.AdapterCviaje
import com.example.roq.adapters.AdapterRViaje
import com.example.roq.dataclass.ModelCviaje
import kotlinx.android.synthetic.main.activity_recycler_crear_viaje.*

class RecyclerCrearViaje : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_crear_viaje)

        val listac= listOf<ModelCviaje>(
            ModelCviaje("Celaya", "12:00", "Omar", "16980598"),
            ModelCviaje("Celaya", "12:00", "Omar", "16980598"),
            ModelCviaje("Celaya", "12:00", "Omar", "16980598"),
            ModelCviaje("Celaya", "12:00", "Omar", "16980598"),
            ModelCviaje("Celaya", "12:00", "Omar", "16980598"),
            ModelCviaje("Celaya", "12:00", "Omar", "16980598"),
            ModelCviaje("Celaya", "12:00", "Omar", "16980598"),
            ModelCviaje("Celaya", "12:00", "Omar", "16980598")

        )
        val recyclercviaje=findViewById<RecyclerView>(R.id.recyclercviaje)
        val adapter= AdapterCviaje(this)
        recyclercviaje.adapter=adapter

        //SELECCCIONAMOS CMO QUEREMOS QUE SE MUESTRE LA CARD DEL RECYCKERVIEW
        recyclercviaje.layoutManager = LinearLayoutManager(this)
        //recycler.layoutManager= GridLayoutManager(this,2)
        adapter.setDataToList(listac)
    }
    fun enviarcrearviaje (v:View){
        val intent= Intent(this, Activitycrearviaje:: class.java)
        startActivity(intent)
    }

}
