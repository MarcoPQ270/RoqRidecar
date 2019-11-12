package com.example.roq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_registro.*
import kotlinx.android.synthetic.main.activity_main_registro.edtnip

class MainRegistro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registro)
    }

    fun valida(V: View) {
        if (edtcontrolreg.text!!.isEmpty()) {
            Toast.makeText(this, "No se agrego el numero de control", Toast.LENGTH_LONG).show()
            edtcontrolreg.requestFocus()
        } else if (edtnombrereg.text!!.isEmpty()) {
            Toast.makeText(this, "No se agrego el nombre", Toast.LENGTH_LONG).show()
            edtnombrereg.requestFocus()
        } else if (edtcorreoreg.text!!.isEmpty()) {
            Toast.makeText(this, "No se agrego el numero de control", Toast.LENGTH_LONG).show()
            edtcorreoreg.requestFocus()
        } else if (edtnipconfirma.text!!.isEmpty()) {
            Toast.makeText(this, "No se agrego el nip de confirmacion", Toast.LENGTH_LONG).show()
            edtnipconfirma.requestFocus()
        } else if (edtnipregis.text!!.isEmpty()) {
            Toast.makeText(this, "No se agrego el nip", Toast.LENGTH_LONG).show()
            edtnipregis.requestFocus()
        }
    }
}

