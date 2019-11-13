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
import com.example.roq.MainActivityPerfil
import com.example.roq.R
import com.example.roq.dataclass.Estudiantes
import com.example.roq.dataclass.ModelCviaje

class AdapterRViaje internal constructor(context: Context):
    RecyclerView.Adapter<AdapterRViaje.estvijviewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var Listavij= emptyList<ModelCviaje>()

    fun setDataToList(lista:List<ModelCviaje>){
        this.Listavij=lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): estvijviewHolder {
        val itemView=inflater.inflate(R.layout.recycler_item,parent,false)
        return estvijviewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return Listavij.size
    }

    override fun onBindViewHolder(holder: estvijviewHolder, position: Int) {
        holder.destino.text=Listavij.get(position).municipio
        holder.horasal.text=Listavij.get(position).horas
        holder.nombre.text=Listavij.get(position).nombrec
        holder.nocontrol.text=Listavij.get(position).nocontrol
        var intent = Intent(inflater.context, MainActivityPerfil::class.java)

        holder.card.setOnClickListener{
            val context=inflater.context as Activity
            context.startActivity(intent)
        }

    }

    inner class estvijviewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val card= itemView.findViewById<ConstraintLayout>(R.id.itemCardchof)
        val destino= itemView.findViewById<TextView>(R.id.item_destinochof)
        val horasal=itemView.findViewById<TextView>(R.id.item_horasalchof)
        val nombre=itemView.findViewById<TextView>(R.id.item_nombrechof)
        val nocontrol=itemView.findViewById<TextView>(R.id.item_nocontrolchof)
    }
}



