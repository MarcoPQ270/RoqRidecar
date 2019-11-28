package com.example.roq.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roq.R
import com.example.roq.dataclass.Estdetalle
import kotlinx.android.synthetic.main.recycler_item_detalleviaje.view.*

class Adapterdetalleviaje(private var mListadetalle:List<Estdetalle>, private val mContext:Context, private val clickListener:(Estdetalle)->Unit ):
RecyclerView.Adapter<Adapterdetalleviaje.estdetalleviewholder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): estdetalleviewholder {
        val layoutInflater = LayoutInflater.from(mContext)
        return estdetalleviewholder(layoutInflater.inflate(R.layout.recycler_item_detalleviaje, parent, false))
    }
    override fun onBindViewHolder(holder: estdetalleviewholder, position: Int) {
        holder.bind(mListadetalle[position], mContext, clickListener)

}
    override fun getItemCount(): Int =mListadetalle.size

    fun setTask(Estdetalle: List<Estdetalle>) {
        mListadetalle = Estdetalle
        notifyDataSetChanged()
    }
    fun getTasks(): List<Estdetalle> = mListadetalle


    class estdetalleviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(detalle:Estdetalle, context: Context, clickListener: (Estdetalle) -> Unit) {

            itemView.nombreestudiante.text = detalle.nombree.toString()
            itemView.nocontrolestudiante.text=detalle.nocon.toString()
            itemView.carreraestudiante.text=detalle.carr.toString()
            itemView.semestreestudiante.text=detalle.semes.toString()
          //  itemView.item_nota.text=detalle.note.toString()
            itemView.setOnClickListener{clickListener(detalle)}

        }
    }
}