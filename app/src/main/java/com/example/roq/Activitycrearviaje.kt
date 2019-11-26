package com.example.roq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_activitycrearviaje.*

class Activitycrearviaje : AppCompatActivity() {

    var des: String = ""
    var horas: String = ""
    var nota: String = ""
    var control: String = ""
    var nombre: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activitycrearviaje)

        floatingActionButton.setOnClickListener {
            if (edtdestino.text!!.isEmpty() || edthorasal.text!!.isEmpty() ||
                edtnota.text!!.isEmpty() || edtcontrol.text!!.isEmpty() || edtnombrechof.text!!.isEmpty())
            {
                Toast.makeText(this, "Falta información del Estudiante", Toast.LENGTH_SHORT).show();
                edtdestino.requestFocus()
            }
            else
            {
                leerCajas()
                val sentencia  = "INSERT INTO viajes(destino,horas,nota,nocontrol,nomest) " +
                        " values ('$des','$horas','$nota','$control','$nombre')"
                val admin = adBD(this)
                if (admin.Ejecuta(sentencia) == 1)
                {
                    Toast.makeText(this, "Se Agrego el viaje", Toast.LENGTH_SHORT).show();
                    limpiarCajas()
                    var actividad = Intent(this,RecyclerCrearViaje::class.java)
                    startActivity(actividad)
                }
                else
                {
                    Toast.makeText(this, "Viaje NO AGREGADO", Toast.LENGTH_SHORT).show();
                    edtdestino.requestFocus()
                }

            }

        }
    }

    fun leerCajas()
    {
        des = edtdestino.text.toString()
        horas = edthorasal.text.toString()
        nota = edtnota.text.toString()
        control = edtcontrol.text.toString()
        nombre = edtnombrechof.text.toString()
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
       // btnAgregar.isEnabled = true
        //btnModificar.isEnabled = false
        //btnEliminar.isEnabled = false
        edtdestino.requestFocus()
    }


}
