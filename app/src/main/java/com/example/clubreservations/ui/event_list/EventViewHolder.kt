package com.example.clubreservations.ui.event_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.clubreservations.databinding.ItemEventBinding
import com.example.clubreservations.model.Event

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(event: Event) {
        val binding = ItemEventBinding.bind(itemView)
        binding.itemEventTitle.text = event.title
    }

}