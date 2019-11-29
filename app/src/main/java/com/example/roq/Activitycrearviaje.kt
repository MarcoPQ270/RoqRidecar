package com.example.roq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.roq.APIVolley.VolleySingleton
import kotlinx.android.synthetic.main.activity_activitycrearviaje.*
import kotlinx.android.synthetic.main.activity_main_registro.*
import org.json.JSONObject

class Activitycrearviaje : AppCompatActivity() {

    //Adaptador de LAN inalámbrica Conexión de área local* 2 se conecta con ese
    val wsInsertarviaje = address.ip + "Services/Insertarviaje.php"
    var des: String = ""
    var horas: String = ""
    var nota: String = ""
    var control: String = ""
    var nombre: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activitycrearviaje)

    }
    fun addtravel(v: View){
        if (!edtdestino.text!!.isEmpty() || !edthorasal.text!!.isEmpty() ||
                    !edtnota.text!!.isEmpty() || !edtcontrol.text!!.isEmpty() || !edtnombrechof.text!!.isEmpty()) {
            edtdestino.requestFocus()
            leerCajas()
            val database = adBD(this)
            val tupla = database.Ejecuta(
                "INSERT INTO viajes(destino,horas,nota,nocontrol,nomest) VALUES(" +
            "'$des'," +
                    "'$horas'," +
                    "'$nota'," +
                    "'$control'," +
                    "'$nombre')"
            )
            if (tupla == 1) {
                limpiarCajas()
            } else {

            }
          startActivity(Intent(this,RecyclerCrearViaje::class.java))
        } else {
            Toast.makeText(this, "No puedes dejar ningún campo de texto vacio", Toast.LENGTH_SHORT).show()
        }
    }
    fun leerCajas()
    {
        des = edtdestino.text.toString()
        horas = edthorasal.text.toString()
        nota = edtnota.text.toString()
        control = edtcontrol.text.toString()
        nombre = edtnombrechof.text.toString()

        var jsonEntrada= JSONObject()

        jsonEntrada.put("destino", des)
        jsonEntrada.put("horas", horas)
        jsonEntrada.put("nota",nota)
        jsonEntrada.put("nocontrol",control)
        jsonEntrada.put("nomest",nombre)


        sendRequest(wsInsertarviaje,jsonEntrada)
    }

    fun limpiarCajas()
    {
        des=""
        horas=""
        nota=""
        control=""
        nombre=""
        edtdestino.setText("")
        edthorasal.setText("")
        edtnota.setText("")
        edtcontrol.setText("")
        edtnombrechof.setText("")
        edtdestino.requestFocus()
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
