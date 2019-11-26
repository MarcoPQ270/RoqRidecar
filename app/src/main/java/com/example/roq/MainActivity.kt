package com.example.roq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // btnIniciar.setOnClickListener {
       //     val admin = adBD(this)
        //    val result = admin.consulta("Select nomest from usuarios Where nocontrol = '16980598'")
//     }
    }
    fun validasesion(v: View){
        if (edtcontrolregistro.text!!.isEmpty()){
            Toast.makeText(this,"Numero de control vacio", Toast.LENGTH_LONG).show()
            edtcontrolregistro.requestFocus()
        }else if (edtnip.text!!.isEmpty()){
            Toast.makeText(this,"El nip esta vacio", Toast.LENGTH_LONG).show()
            edtnip.requestFocus()
        }else{
            val intent=Intent(this, RecyclerViajes::class.java)
            startActivity(intent)
        }
    }

    //Funcion para mandar al usuario a la pantalla de registro y registre los datos solicitados
    fun enviaralregistro(v: View){
        val intent=Intent(this, MainRegistro::class.java)
        startActivity(intent)
    }
    fun enviaradmin(v:View){
        val intent=Intent(this, RecyclerCrearViaje:: class.java)
        startActivity(intent)
    }


}
