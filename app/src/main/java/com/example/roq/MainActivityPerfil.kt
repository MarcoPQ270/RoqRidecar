package com.example.roq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_perfil.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.roq.APIVolley.VolleySingleton
import kotlinx.android.synthetic.main.activity_activitycrearviaje.*
import kotlinx.android.synthetic.main.activity_main_perfil.control
import kotlinx.android.synthetic.main.activity_main_registro.*
import org.json.JSONObject


class MainActivityPerfil : AppCompatActivity() {

    val wsInsertar = address.ip + "Services/insertarpeticion.php"

    var nocontrol:String=""
    var nombre:String=""
    var carrera:String=""
    var semestre:String=""
    var nomc:String=""
    var controlchof:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_perfil)
        val actividad = intent
         controlchof = actividad.getStringExtra("nocontrol")
         nomc = actividad.getStringExtra("nomest")

        tvDestino.text = nomc
        tvControl.text= controlchof

    }

    fun sendpeticion (v: View){
        if (!edtcontrolalumno.text!!.isEmpty() && !edtnombrealumno.text!!.isEmpty() && !edtcarreraalumno.text!!.isEmpty() && !edtsemestrealumno!!.text!!.isEmpty()) {
            getValues()
            val database = adBD(this)
            val tupla = database.Ejecuta(
                "INSERT INTO peticion(nocontrol,nombre,carrera,semestre,controlchofer) VALUES(" +
                        "'$nocontrol'," +
                        "'$nombre'," +
                        "'$carrera'," +
                        "'$semestre'," +
                        "'$controlchof')"
            )
            if (tupla == 1) {

                clearFields()
            } else {

            }

            startActivity(Intent(this,MainActivity::class.java))
        } else {
            Toast.makeText(this, "No puedes dejar ningún campo de texto vacio", Toast.LENGTH_SHORT).show()
        }


    }
    fun getValues(){
        nocontrol=edtcontrolalumno.text.toString()
        nombre=edtnombrealumno.text.toString()
        carrera=edtcarreraalumno.text.toString()
        semestre=edtsemestrealumno.text.toString()
        controlchof=tvControl.text.toString()

        var jsonEntrada= JSONObject()

        jsonEntrada.put("nocontrol", nocontrol)
        jsonEntrada.put("nombre", nombre)
        jsonEntrada.put("carrera",carrera)
        jsonEntrada.put("semestre", semestre)
        jsonEntrada.put("controlchofer", controlchof)

        sendRequest(wsInsertar,jsonEntrada)
    }
    fun clearFields(){
        nocontrol=""
        carrera=""
        nombre=""
        semestre=""
        nomc=""
        edtcontrolalumno.setText("")
        edtnombrealumno.setText("")
        edtcarreraalumno.setText("")
        edtsemestrealumno.setText("")
        edtcontrolalumno.requestFocus()
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
