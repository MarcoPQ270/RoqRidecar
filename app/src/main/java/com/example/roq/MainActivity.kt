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
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
   private lateinit var sControl:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        finish()

        val actividad = intent
        if (actividad != null && actividad.hasExtra("nocontrol")) {
            sControl = actividad.getStringExtra("nocontrol")
            startActivity(Intent(this,RecyclerViajes::class.java))
        } else {
            val admin = adBD(this)
            val result = admin.consulta("Select nocontrol from usuarios")
            if (result!!.moveToFirst()) {
                sControl = result.getString(0)
                result.close()
                admin.close()
                startActivity(Intent(this,RecyclerViajes::class.java))
            } else {
                val actividadLog = Intent(this, ActivityLogin::class.java)
                startActivity(actividadLog)
            }
        }
    }
}