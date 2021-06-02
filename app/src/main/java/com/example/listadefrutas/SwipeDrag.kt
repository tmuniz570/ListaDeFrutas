package com.example.listadefrutas

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeDrag (private val swipeDragAdapter: SwipeDragAdapter) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val swipe = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val drag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(drag, swipe)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val initPosition = viewHolder.adapterPosition
        val targetPosition = target.adapterPosition
        swipeDragAdapter.swap(initPosition, targetPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        swipeDragAdapter.remove(position)
    }

}