package com.example.listadefrutas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadefrutas.databinding.ItemBinding
import java.util.*

class SwipeDragAdapter(private val frutas: MutableList<DCFruta>, private val callback: (DCFruta, Int) -> Unit) : RecyclerView.Adapter<SwipeDragAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val binding = ItemBinding.bind(view)

        val vh = VH(binding)

        vh.itemView.setOnClickListener {
            val fruta = frutas[vh.adapterPosition]
            callback(fruta, vh.adapterPosition)
        }

        return vh
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val fruta = frutas[position]
        holder.setValues(fruta.nome!!, fruta.desc!!)
    }

    override fun getItemCount() = frutas.size

    fun remove(position: Int) {
        frutas.removeAt(position)
        notifyItemRemoved(position)
    }

    fun swap(initPosition: Int, targetPosition: Int) {
        Collections.swap(frutas, initPosition, targetPosition)
        notifyItemMoved(initPosition, targetPosition)
    }

    class VH(itemView: ItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val title = itemView.itemNome
        private val description = itemView.itemDesc

        fun setValues(title: String, description: String) {
            this.title.text = title
            this.description.text = description
        }
    }

}