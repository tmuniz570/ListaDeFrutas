package com.example.listadefrutas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterFruta (private var clickListener: OnClickListener) :
    RecyclerView.Adapter<AdapterFruta.HolderData>() {

    private var listaFrutas = ArrayList<DCFruta>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderData {
        val rHD = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return HolderData(rHD)
    }

    override fun onBindViewHolder(holder: HolderData, position: Int) {
        val data = listaFrutas[position]

        holder.nome.text = data.nome
        holder.desc.text = data.desc
        holder.initializeClick(listaFrutas, clickListener)
    }

    override fun getItemCount(): Int {
        return listaFrutas.size
    }

    class HolderData(v: View) : RecyclerView.ViewHolder(v) {
        var nome = v.findViewById<TextView>(R.id.itemNome)
        var desc = v.findViewById<TextView>(R.id.itemDesc)

        fun initializeClick(item: ArrayList<DCFruta>, action: OnClickListener) {

            itemView.setOnClickListener {
                action.onItemClick(item[position], adapterPosition)
            }
        }
    }

    fun get(frutas: ArrayList<DCFruta>) {
        listaFrutas = frutas
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(item: DCFruta, position: Int) {

        }
    }

}