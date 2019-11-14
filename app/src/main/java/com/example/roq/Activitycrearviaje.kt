package com.example.roq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_activitycrearviaje.*

class Activitycrearviaje : AppCompatActivity() {
    var control:String=""

    var destino:String=""
    var horas:String=""
    var nota:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activitycrearviaje)
    }
    fun addtravel(v:View){
        if (!edtdestino.text!!.isEmpty() && !edthorasal.text!!.isEmpty() && !edtnota.text!!.isEmpty()) {
            getValues()
            val database=adBD(this)
            val result = database.consulta("Select nocontrol From usuarios")
            if (result!!.moveToFirst()) {
                control = result.getString(0)
                result.close()
            }
            val tupla=database.Ejecuta("INSERT INTO viaje(destino,horas,nota,nocontrol) VALUES(" +
                    "'$destino'," +
                    "'$horas'," +
                    "'$nota'," +
                    "'$control')")
            if(tupla!=null){
                Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show()
                clearFields()
            }else{
                Toast.makeText(this, "Error al insertar", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "No puedes dejar ningún campo de texto vacio", Toast.LENGTH_SHORT).show()
        }
    }
    fun getValues(){
        destino=edtdestino.text.toString()
        horas=edthorasal.text.toString()
        nota=edtnota.text.toString()
    }
    fun clearFields(){
        control=""
        destino=""
        horas=""
        nota=""

        edtdestino.setText("")
        edthorasal.setText("")
        edtnota.setText("")
    }
}
