package com.example.roq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.example.roq.adapters.AdapterCviaje
import com.example.roq.adapters.AdapterRViaje
import com.example.roq.dataclass.Estudiantes
import com.example.roq.dataclass.ModelCviaje
import kotlinx.android.synthetic.main.activity_recycler_crear_viaje.*
import kotlinx.android.synthetic.main.activity_recycler_viajes.*

class RecyclerViajes : AppCompatActivity() {

    private lateinit var viewAdapter: AdapterRViaje
    private lateinit var viewManager: RecyclerView.LayoutManager
    val CviajeList: List<ModelCviaje> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_viajes)
        viewManager = LinearLayoutManager(this)
        viewAdapter = AdapterRViaje(CviajeList, this, {viaje: ModelCviaje -> onItemClickListener(viaje)})

        recyclerest.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(this@RecyclerViajes, DividerItemDecoration.VERTICAL))

        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val viaje= viewAdapter.getTasks()
                val admin = adBD(baseContext)
                if (admin.Ejecuta("DELETE FROM viajes WHERE iddestino=" + viaje[position].id) == 1){
                    retrieveCviaje()
                }
            }
        }).attachToRecyclerView(recyclercviaje)

    }
    private fun onItemClickListener(viaje: ModelCviaje) {
        Toast.makeText(this, "Clicked item" + viaje.nomc, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        retrieveCviaje()
    }

    private fun retrieveCviaje() {
        val Cviaje = getviajes()
        viewAdapter.setTask(Cviaje!!)
    }

    fun getviajes(): MutableList<ModelCviaje>{
        var cviaje:MutableList<ModelCviaje> = ArrayList()
        val admin = adBD(this)

        //                                           0          1      2    3     4         5
        val tupla = admin.consulta("SELECT iddestino, destino,horas,nota,nocontrol,nomest FROM viajes ORDER BY destino")
        while (tupla!!.moveToNext()) {
            val id = tupla.getString(0)
            val des = tupla.getString(1)
            val hos = tupla.getString(2)
            val not = tupla.getString(3)
            val cont = tupla.getString(4)
            val nom = tupla.getString(5)

            cviaje.add(ModelCviaje(""+id,"Destino: "+des,"Hora de salida: "+hos,"Nota: "+not, "No Control: "+cont,"Nombre: "+nom))
        }
        tupla.close()
        admin.close()
        return cviaje
    }

}
