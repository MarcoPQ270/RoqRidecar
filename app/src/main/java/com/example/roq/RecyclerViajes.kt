package com.example.roq

import android.content.Intent
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
import kotlinx.android.synthetic.main.recycler_item.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class RecyclerViajes : AppCompatActivity() {

    private lateinit var viewAdapter: AdapterRViaje
    private lateinit var viewManager: RecyclerView.LayoutManager
    val CviajeList: List<ModelCviaje> = ArrayList()

    var id:String=""
    var dest:String=""
    var hos:String = ""
    var not:String=""
    var cont:String =""
    var nom:String=""

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

            }
        }).attachToRecyclerView(recyclercviaje)

    }
    private fun onItemClickListener(viaje: ModelCviaje) {
        Toast.makeText(this, "Clicked item" + viaje.nomc, Toast.LENGTH_LONG).show()
        val actividad = Intent(this, MainActivityPerfil::class.java)
        actividad.putExtra("nomest",viaje.nomc)
        actividad.putExtra("nocontrol",viaje.nocont)
        startActivity(actividad)
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
             id = tupla.getString(0)
             dest = tupla.getString(1)
             hos = tupla.getString(2)
             not = tupla.getString(3)
             cont = tupla.getString(4)
             nom = tupla.getString(5)

            cviaje.add(ModelCviaje(id, dest,hos,not, cont, nom))
        }
        tupla.close()
        admin.close()
        return cviaje
    }
}