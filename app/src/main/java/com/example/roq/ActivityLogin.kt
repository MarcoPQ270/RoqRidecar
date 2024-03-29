package com.example.roq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.roq.APIVolley.VolleySingleton
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class ActivityLogin : AppCompatActivity() {
    var user:String=""
    var Pass:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getviajesWs()
        getvpeticionsWs()
        btniniciar.setOnClickListener {
            validasesion()
        }

    }
    fun validasesion(){
        if(edtcontrolregistro.text!!.isEmpty()){
            Toast.makeText(this,"complete los campos", Toast.LENGTH_SHORT).show()
            edtcontrolregistro.requestFocus()
        }else{
            user=edtcontrolregistro.text.toString()
            Pass=edtnip.text.toString()

            var jsonEntrada = JSONObject()
            jsonEntrada.put("nocontrol",user)
            getuser(jsonEntrada)
            btniniciar.hide()
            floatingActionButtonregistrar.hide()
            btniniciar2.show()
        }
    }
    fun login2(v: View) {
        if (user=="16980598" && Pass=="2824") {
            val intent= Intent(this, RecyclerCrearViaje::class.java)
            startActivity(intent)
            finish()
        } else{
        val admin = adBD(this)
        val result = admin.consulta("Select nocontrol,nip From usuarios")
        if (result!!.moveToFirst()) {
            var snip = result.getString(1)
            if (Pass == snip) {
                val intent = Intent(this, RecyclerViajes::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Su numero de control o nip son incoreectos",
                    Toast.LENGTH_SHORT
                ).show()
                edtcontrolregistro.requestFocus()
            }
        }
    }
    }

    //Funcion para mandar al usuario a la pantalla de registro y registre los datos solicitados
    fun enviaralregistro(v: View){
        val intent= Intent(this, MainRegistro::class.java)
        startActivity(intent)

    }
    fun enviaradmin(v: View){
        val intent= Intent(this, RecyclerCrearViaje:: class.java)
        startActivity(intent)
        finish()
    }

    fun getviajesWs() { //funcion que carga la informacion de MySQL a SQLite
        val wsURL = address.ip+ "Services/Mostrarviajes.php"
        val admin = adBD(this)
        admin.Ejecuta("DELETE FROM viajes")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, wsURL, null,
            Response.Listener { response ->
                val succ = response["success"]
                val msg = response["message"]
                val viajeJson = response.getJSONArray("viaje")//name usuario (webservice)
                for (i in 0 until viajeJson.length()) {
                    val idd = viajeJson.getJSONObject(i).getString("iddestino")
                    val des = viajeJson.getJSONObject(i).getString("destino")
                    val hors = viajeJson.getJSONObject(i).getString("horas")
                    val not = viajeJson.getJSONObject(i).getString("nota")
                    val nocont =viajeJson.getJSONObject(i).getString("nocontrol")
                    val nomc=viajeJson.getJSONObject(i).getString("nomest")
                    val sentencia = "INSERT INTO viajes(iddestino,destino,horas,nota,nocontrol,nomest) VALUES(" +
                            "'$idd'," +
                            "'$des'," +
                            "'$hors'," +
                            "'$not'," +
                            "'$nocont'," +
                            "'$nomc')"
                    var result = admin.Ejecuta(sentencia)
                   // Toast.makeText(this, "Información cargada: " + result, Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                //Toast.makeText(this, "Error capa8: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
    fun getvpeticionsWs() { //funcion que carga la informacion de MySQL a SQLite
        val wsURL = address.ip+ "Services/consultapeticion.php"
        val admin = adBD(this)
        admin.Ejecuta("DELETE FROM peticion")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, wsURL, null,
            Response.Listener { response ->
                val succ = response["success"]
                val msg = response["message"]
                val peticionJson = response.getJSONArray("peticion")//name usuario (webservice)
                for (i in 0 until peticionJson.length()) {
                    val noce = peticionJson.getJSONObject(i).getString("nocontrol")
                    val nome = peticionJson.getJSONObject(i).getString("nombre")
                    val carre = peticionJson.getJSONObject(i).getString("carrera")
                    val semese = peticionJson.getJSONObject(i).getString("semestre")
                    val controlchof =peticionJson.getJSONObject(i).getString("controlchofer")
                    val sentencia = "INSERT INTO peticion(nocontrol,nombre,carrera,semestre,controlchofer) VALUES(" +
                            "'$noce'," +
                            "'$nome'," +
                            "'$carre'," +
                            "'$semese'," +
                            "'$controlchof')"
                    var result = admin.Ejecuta(sentencia)
                  ///  Toast.makeText(this, "Información cargada: " + result, Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                //Toast.makeText(this, "Error capa8: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    fun getuser(jsonEnt: JSONObject){
        val wsURL = address.ip + "Services/MostrarAlumno.php"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, wsURL, jsonEnt,

            Response.Listener { response ->
                val succ = response["success"]
                val msg = response["message"]
                val userJson = response.getJSONArray("alumno")
                if (userJson.length()>0){
                    val admin = adBD(this)
                    val ncontrol = userJson.getJSONObject(0).getString("nocontrol")
                    val nip = userJson.getJSONObject(0).getString("nip")
                    val sentencia = "INSERT INTO usuarios(nocontrol,nomest,correo,nip,carrera,semestre) Values('$ncontrol','1','1','$nip','1','1')"
                    var result = admin.Ejecuta(sentencia)
                    Toast.makeText(this,"usuario " + result, Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                //Toast.makeText(this, "Se necesita una conexión a internet: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

}
