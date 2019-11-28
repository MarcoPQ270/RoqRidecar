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
import com.example.roq.MainActivityPerfil
import com.example.roq.R
import com.example.roq.dataclass.Estudiantes
import com.example.roq.dataclass.ModelCviaje
import kotlinx.android.synthetic.main.recycler_item.view.*
import kotlinx.android.synthetic.main.recycler_item_cviaje.view.*

class AdapterRViaje (private var mListaCviaje:List<ModelCviaje>, private val mContext:Context, private val clickListener:(ModelCviaje)-> Unit):
    RecyclerView.Adapter<AdapterRViaje.estvijviewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): estvijviewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        return AdapterRViaje.estvijviewHolder(layoutInflater.inflate(R.layout.recycler_item, parent, false))
    }
    override fun onBindViewHolder(holder: estvijviewHolder, position: Int) {
        holder.bind(mListaCviaje[position], mContext, clickListener)
    }

    override fun getItemCount(): Int = mListaCviaje.size

    fun setTask(ModelCviaje: List<ModelCviaje>) {
        mListaCviaje = ModelCviaje
        notifyDataSetChanged()
    }
    fun getTasks(): List<ModelCviaje> = mListaCviaje

    class estvijviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(viaje: ModelCviaje, context: Context, clickListener: (ModelCviaje) -> Unit) {

            itemView.tvDestino.text = viaje.municip.toString()
            itemView.tvsalida.text=viaje.hors.toString()
            itemView.tvControl.text=viaje.nocont.toString()
            itemView.tvnombre.text=viaje.nomc.toString()
            itemView.tvnota.text=viaje.note.toString()
            itemView.setOnClickListener{clickListener(viaje)}
        }
    }
}



