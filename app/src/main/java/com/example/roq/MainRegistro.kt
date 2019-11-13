package com.example.roq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_registro.*
import kotlinx.android.synthetic.main.activity_main_registro.edtnip

class MainRegistro : AppCompatActivity() {

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
    //metodo para agregar ususarios del registro
    fun addStudent(v: View) {
        if (!edtcontrolreg.text!!.isEmpty() && !edtnombrereg.text!!.isEmpty() && !edtcorreoreg.text!!.isEmpty() && !edtnipregis!!.text!!.isEmpty()
            && !edtnipconfirma!!.text!!.isEmpty() && !edtsemestrereg!!.text!!.isEmpty()) {
            getValues()
            val database = adBD(this)
            val tupla = database.Ejecuta(
                "INSERT INTO usuarios(nocontrol,nomest,correo,nip,carrera,semestre) VALUES(" +
                        "'$control'," +
                        "'$nombre'," +
                        "'$correo'," +
                        "'$nip'," +
                        "'$carrera'," +
                        "'$semestre')"
            )
            if (tupla == 1) {
                Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()
                clearFields()
            } else {
                Toast.makeText(this, "Error al Registrar", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "No puedes dejar ningún campo de texto vacio", Toast.LENGTH_SHORT).show()
        }
    }
    fun getValues(){
        control=edtcontrolreg.text.toString()
        nombre=edtnombrereg.text.toString()
        carrera=edtcarrerareg.text.toString()
        correo=edtcorreoreg.text.toString()
        nip=edtnipregis.text.toString()
        confirmanip=edtnipconfirma.text.toString()
        semestre=edtsemestrereg.text.toString()
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
}