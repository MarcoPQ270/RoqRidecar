package com.example.roq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.roq.APIVolley.VolleySingleton
import com.example.roq.adapters.AdapterCviaje
import com.example.roq.adapters.AdapterRViaje
import com.example.roq.dataclass.ModelCviaje
import kotlinx.android.synthetic.main.activity_recycler_crear_viaje.*
import org.json.JSONObject

class RecyclerCrearViaje : AppCompatActivity() {

    private lateinit var viewAdapter: AdapterCviaje
    private lateinit var viewManager: RecyclerView.LayoutManager
    val CviajeList: List<ModelCviaje> = ArrayList()

        var id: String=""
        var des:String=""
        var hos:String=""
        var not:String=""
        var cont: String=""
        var nom: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_crear_viaje)


        viewManager = LinearLayoutManager(this)
        viewAdapter = AdapterCviaje(CviajeList, this, { viaje: ModelCviaje -> onItemClickListener(viaje) })

        recyclercviaje.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(this@RecyclerCrearViaje, DividerItemDecoration.VERTICAL))

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

                    val iddes = viaje[position].id
                    val wsEliminar = address.ip+ "Services/eliminarviaje.php"
                    var jsonEntrada= JSONObject()

                    jsonEntrada.put("iddestino", iddes)
                    sendRequest(wsEliminar,jsonEntrada)

                }
            }
        }).attachToRecyclerView(recyclercviaje)

        btncrearnuevoviaje.setOnClickListener {
            val intent= Intent(this, Activitycrearviaje:: class.java)
            startActivity(intent)
        }
    }


    // Evento clic cuando damos clic en un elemento del Recyclerview
    private fun onItemClickListener(viaje: ModelCviaje) {
        Toast.makeText(this, "Clicked item" + viaje.nomc, Toast.LENGTH_LONG).show()
        var nocont = viaje.nocont
        val actividad= Intent(this,ActivityDetallevieje::class.java)
        actividad.putExtra("NOC",nocont)
        startActivity(actividad)
    }
    override fun onResume() {
        super.onResume()
        retrieveCviaje()
    }
    private fun retrieveCviaje() {
        val Cviaje = getviajes()
        viewAdapter.setTask(Cviaje)
    }

    fun getviajes(): MutableList<ModelCviaje>{
        var cviaje:MutableList<ModelCviaje> = ArrayList()
        val admin = adBD(this)

        //                                           0          1      2    3     4         5
        val tupla = admin.consulta("SELECT iddestino, destino,horas,nota,nocontrol,nomest FROM viajes ORDER BY destino")
        while (tupla!!.moveToNext()) {
                id = tupla.getString(0)
                des = tupla.getString(1)
                hos = tupla.getString(2)
                not = tupla.getString(3)
                cont = tupla.getString(4)
                nom = tupla.getString(5)

            cviaje.add(ModelCviaje(id,des,hos,not, cont,nom))
        }
        tupla.close()
        admin.close()
        return cviaje
    }
    fun sendRequest(wsUrl:String,jsonEntrada: JSONObject){

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, wsUrl, jsonEntrada,
            Response.Listener { response ->
                val succ = response["success"]
                val msg = response["message"]
                Toast.makeText(this, "Success: ${succ} Message:${msg} ", Toast.LENGTH_LONG).show();
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "${error.message}", Toast.LENGTH_LONG).show();
                Log.d("ERROR","${error.message}")
                Toast.makeText(this, "API: Error de capa 8 WS):", Toast.LENGTH_LONG).show();
            }
        )
        //Es para agregar las peticiones a la cola
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}
