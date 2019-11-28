package com.example.roq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roq.adapters.AdapterRViaje
import com.example.roq.adapters.Adapterdetalleviaje
import com.example.roq.dataclass.Estdetalle
import com.example.roq.dataclass.Estudiantes
import com.example.roq.dataclass.ModelCviaje
import kotlinx.android.synthetic.main.activity_detallevieje.*
import kotlinx.android.synthetic.main.activity_detallevieje.view.*
import kotlinx.android.synthetic.main.activity_recycler_crear_viaje.*

class ActivityDetallevieje : AppCompatActivity() {

    private lateinit var viewAdapter: Adapterdetalleviaje
    private lateinit var viewManager: RecyclerView.LayoutManager
    val DetalleList: List<Estdetalle> = ArrayList()

    var nombreest:String=""
    var nocontrolest:String=""
    var carreraest:String = ""
    var semestreestu:String=""
    var contrlchof:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detallevieje)
        viewManager = LinearLayoutManager(this)
        viewAdapter = Adapterdetalleviaje(DetalleList, this, {detalle: Estdetalle -> onItemClickListener(detalle)})

        recyclerdetalles.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(this@ActivityDetallevieje, DividerItemDecoration.VERTICAL))
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val detalle= viewAdapter.getTasks()
                val admin = adBD(baseContext)
                if (admin.Ejecuta("DELETE FROM peticion WHERE nocontrol=" + detalle[position].nocon) == 1){
                    retrieveDetalles()
                }
            }
        }).attachToRecyclerView(recyclerdetalles)
    }
    private fun onItemClickListener(detalle: Estdetalle) {
        Toast.makeText(this, "Clicked item" + detalle.nombree, Toast.LENGTH_LONG).show()
    }
    override fun onResume() {
        super.onResume()
        retrieveDetalles()
    }
    private fun retrieveDetalles() {
        val Detalles = getdetalles()
        viewAdapter.setTask(Detalles)
    }
    fun getdetalles(): MutableList<Estdetalle>{
        var detalles:MutableList<Estdetalle> = ArrayList()
        val admin = adBD(this)

        //                                           0          1      2    3     4
        val tupla = admin.consulta("SELECT nocontrol, nombre,carrera,semestre,controlchofer FROM peticion ORDER BY nombre")
        while (tupla!!.moveToNext()) {
            nocontrolest = tupla.getString(0)
            nombreest = tupla.getString(1)
            carreraest= tupla.getString(2)
            semestreestu= tupla.getString(3)

            detalles.add(Estdetalle(nocontrolest,nombreest,semestreestu,carreraest,contrlchof))
        }
        tupla.close()
        admin.close()
        return detalles
    }

}
