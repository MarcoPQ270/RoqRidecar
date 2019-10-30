package com.example.roq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //Funcion para mandar al usuario a la pantalla de registro y registre los datos solicitados
    fun enviaralregistro(v: View){
        val intent=Intent(this, MainRegistro::class.java)
        startActivity(intent)
    }
    fun enviarrecycler(v:View){
        val intent=Intent(this, RecyclerViajes::class.java)
        startActivity(intent)
    }

}
