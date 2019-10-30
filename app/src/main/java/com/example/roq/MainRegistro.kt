package com.example.roq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_registro.*

class MainRegistro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registro)
    }
//Funcion para validar los datos del alumno y enviarlos a la base de datos
    fun RegistrarAlumno(v:View){
        if(edtcontrol.text.isEmpty()){
            Toast.makeText(this,"El campo numero de control esta vacio",Toast.LENGTH_LONG).show()
            edtcontrol.requestFocus()
        }else if(edtnip.text.isEmpty()){
            Toast.makeText(this,"El campo nip esta vacio",Toast.LENGTH_LONG).show()
            edtnip.requestFocus()
        }else if(edtnombre.text.isEmpty()){
            Toast.makeText(this,"El campo nombre esta vacio",Toast.LENGTH_LONG).show()
            edtnombre.requestFocus()
        }else if(edtcarrera.text.isEmpty()){
            Toast.makeText(this,"El campos carrera esta vacio",Toast.LENGTH_LONG).show()
            edtcarrera.requestFocus()
        }else if(edtmunicipio.text.isEmpty()){
            Toast.makeText(this,"El campo municipio esta vacio",Toast.LENGTH_LONG).show()
            edtmunicipio.requestFocus()
        }else{

        }
    }
}
