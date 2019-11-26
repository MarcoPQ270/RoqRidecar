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
import com.example.roq.R
import com.example.roq.dataclass.ModelCviaje
import kotlinx.android.synthetic.main.recycler_item_cviaje.view.*

class AdapterCviaje(private var mListaCviaje:List<ModelCviaje>, private val mContext:Context, private val clickListener:(ModelCviaje)-> Unit):
    RecyclerView.Adapter<AdapterCviaje.CviajeViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CviajeViewHolder{
        val layoutInflater = LayoutInflater.from(mContext)
        return CviajeViewHolder(layoutInflater.inflate(R.layout.recycler_item_cviaje, parent, false))
    }
    override fun onBindViewHolder(holder: CviajeViewHolder, position: Int) {
        holder.bind(mListaCviaje[position], mContext, clickListener)
    }
    override fun getItemCount(): Int = mListaCviaje.size

    fun setTask(ModelCviaje: List<ModelCviaje>) {
        mListaCviaje = ModelCviaje
        notifyDataSetChanged()
    }
    fun getTasks(): List<ModelCviaje> = mListaCviaje


    class CviajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(viaje: ModelCviaje, context: Context, clickListener: (ModelCviaje) -> Unit) {


            itemView.item_destinochof.text = viaje.municip.toString()
            itemView.item_horasalchof.text=viaje.hors.toString()
            itemView.item_nocontrolchof.text=viaje.nocont.toString()
            itemView.nombrechof.text=viaje.nomc.toString()
            itemView.item_nota.text=viaje.note.toString()


            itemView.setOnClickListener {
                clickListener(viaje)
                var intent = Intent(context, ActivityDetallevieje::class.java)
                context.startActivity(intent)
            }
        }
    }
}