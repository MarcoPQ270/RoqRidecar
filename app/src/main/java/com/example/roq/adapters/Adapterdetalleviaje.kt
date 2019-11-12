package com.example.roq.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.roq.R
import com.example.roq.dataclass.Estdetalle
import com.example.roq.dataclass.Estudiantes

class Adapterdetalleviaje internal constructor(context: Context):
RecyclerView.Adapter<Adapterdetalleviaje.estdetalleviewholder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var ListaEstdetalle= emptyList<Estdetalle>()

    fun setDataToList(lista:List<Estdetalle>){
        this.ListaEstdetalle=lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): estdetalleviewholder {
       val itemview=inflater.inflate(R.layout.activity_detallevieje,parent, false)
        return estdetalleviewholder(itemview)
    }
    override fun getItemCount(): Int {
        return ListaEstdetalle.size
    }
    override fun onBindViewHolder(holder: estdetalleviewholder, position: Int) {
     holder.nombre.text=ListaEstdetalle.get(position).nombree
     holder.nocontrol.text=ListaEstdetalle.get(position).nocon
     holder.carrera.text=ListaEstdetalle.get(position).carr
     holder.semestre.text=ListaEstdetalle.get(position).semes
    }
    inner class estdetalleviewholder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val card = itemview.findViewById<ConstraintLayout>(R.id.itemCarddetalle)
        val nombre = itemview.findViewById<TextView>(R.id.item_nombre)
        val nocontrol=itemview.findViewById<TextView>(R.id.item_nocontrol)
        val carrera=itemview.findViewById<TextView>(R.id.item_carrera)
        val semestre=itemview.findViewById<TextView>(R.id.item_semestre)
        val btnrechazar=itemview.findViewById<Button>(R.id.btnrechazarpas)
        val brnaceptar=itemview.findViewById<Button>(R.id.btnaceptarpas)
    }
}