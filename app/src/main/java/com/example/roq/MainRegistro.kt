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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_registro.*
import kotlinx.android.synthetic.main.activity_main_registro.edtnip
import org.json.JSONObject

class MainRegistro : AppCompatActivity() {
    //Adaptador de LAN inalámbrica Conexión de área local* 2 se conecta con ese
    val wsInsertar = address.ip+"Services/InsertarAlumno.php"

    var control:String=""
    var nombre:String=""
    var correo:String=""
    var nip:String=""
    var confirmanip:String=""
    var carrera:String=""
    var semestre:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registro)
    }
    //metodo para agregar ususarios del registro al base de datos en SQLITE
    fun addStudent(v: View) {
        if (!edtcontrolreg.text!!.isEmpty() && !edtnombrereg.text!!.isEmpty() && !edtcorreoreg.text!!.isEmpty() && !edtnipregis!!.text!!.isEmpty()
            && !edtnipconfirma!!.text!!.isEmpty() && !edtsemestrereg!!.text!!.isEmpty()) {
            getValues()

          //  val database = adBD(this)
           // val tupla = database.Ejecuta(
             //   "INSERT INTO usuarios(nocontrol,nomest,correo,nip,carrera,semestre) VALUES(" +
               //         "'$control'," +
                 //       "'$nombre'," +
                   //     "'$correo'," +
                    //    "'$nip'," +
                      //  "'$carrera'," +
                       // "'$semestre')"
         //   )
           // if (tupla == 1) {

             //   clearFields()
            //} else {

            //}

            startActivity(Intent(this,ActivityLogin::class.java))

            clearFields()
        } else {
            Toast.makeText(this, "No puedes dejar ningún campo de texto vacio", Toast.LENGTH_SHORT).show()
        }
    }

    fun getValues(){
        if (nip==confirmanip){

        control=edtcontrolreg.text.toString()
        nombre=edtnombrereg.text.toString()
        carrera=edtcarrerareg.text.toString()
        correo=edtcorreoreg.text.toString()
        nip=edtnipregis.text.toString()
        confirmanip=edtnipconfirma.text.toString()
        semestre=edtsemestrereg.text.toString()

        var jsonEntrada= JSONObject()

        jsonEntrada.put("nocontrol", control)
        jsonEntrada.put("nomest", nombre)
        jsonEntrada.put("correo",correo)
        jsonEntrada.put("carrera",carrera)
        jsonEntrada.put("nip",nip)
        jsonEntrada.put("semestre", semestre)

        sendRequest(wsInsertar,jsonEntrada)
        }else{
            Toast.makeText(this,"El nip no coincide",Toast.LENGTH_SHORT).show()
        }
    }
    fun clearFields(){
        control=""
        carrera=""
        nombre=""
        correo=""
        nip=""
        confirmanip=""
        semestre=""
        edtcontrolreg.setText("")
        edtnombrereg.setText("")
        edtcorreoreg.setText("")
        edtcarrerareg.setText("")
        edtnipregis.setText("")
        edtnipconfirma.setText("")
        edtsemestrereg.setText("")
        edtcontrolreg.requestFocus()
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