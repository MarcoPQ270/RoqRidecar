package com.example.roq.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.roq.ActivityDetallevieje
import com.example.roq.MainActivity
import com.example.roq.MainActivityPerfil
import com.example.roq.R
import com.example.roq.dataclass.Estudiantes
import com.example.roq.dataclass.ModelCviaje

class AdapterCviaje internal constructor(context: Context): RecyclerView.Adapter<AdapterCviaje.CviajeviewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var ListaCviaje= emptyList<ModelCviaje>()

    fun setDataToList(listaviaje:List<ModelCviaje>){
        this.ListaCviaje=listaviaje
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CviajeviewHolder {
        val itemView=inflater.inflate(R.layout.recycler_item_cviaje, parent, false)
        return CviajeviewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return ListaCviaje.size
    }

    override fun onBindViewHolder(holder: CviajeviewHolder, position: Int) {
        holder.destino.text=ListaCviaje.get(position).municipio
        holder.horasal.text=ListaCviaje.get(position).horas
        holder.nombre.text=ListaCviaje.get(position).nombrec
        holder.nocontrol.text=ListaCviaje.get(position).nocontrol

        var intent = Intent(inflater.context, ActivityDetallevieje::class.java)

        holder.card.setOnClickListener{
            val context=inflater.context as Activity
            context.startActivity(intent)
        }
    }

    inner class CviajeviewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val card= itemView.findViewById<ConstraintLayout>(R.id.itemCardcviaje)
        val destino= itemView.findViewById<TextView>(R.id.item_destino)
        val horasal=itemView.findViewById<TextView>(R.id.item_horasal)
        val nombre=itemView.findViewById<TextView>(R.id.item_nombre)
        val nocontrol=itemView.findViewById<TextView>(R.id.item_nocontrol)
    }
}