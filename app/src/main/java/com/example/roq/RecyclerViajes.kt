package com.example.roq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roq.adapters.AdapterRViaje
import com.example.roq.dataclass.Estudiantes

class RecyclerViajes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_viajes)

        val lista= listOf<Estudiantes>(
            Estudiantes("1680598","2824", "Marco", "7", "Tics","21", "Celaya"),
            Estudiantes("1680598","2824", "Juan", "7", "Tics", "21","Celaya"),
            Estudiantes("1680598","2824", "Marco", "7", "Tics","21", "Celaya"),
            Estudiantes("1680598","2824", "Juan", "7", "Tics", "21","Celaya"),
            Estudiantes("1680598","2824", "Marco", "7", "Tics","21", "Celaya"),
            Estudiantes("1680598","2824", "Juan", "7", "Tics", "21","Celaya")
  )
        val recycler = findViewById<RecyclerView>(R.id.recyclerest)
        val adapter=AdapterRViaje(this)
        recycler.adapter=adapter
        recycler.layoutManager = LinearLayoutManager(this)
        //recycler.layoutManager=GridLayoutManager(this,2)
        adapter.setDataToList(lista)
    }
}
