package com.example.clubreservations.ui.event_list

import android.media.MediaDrm.OnEventListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clubreservations.R
import com.example.clubreservations.model.Event

class EventAdapter : RecyclerView.Adapter<EventViewHolder>() {

    private val events = mutableListOf<Event>()
    var onEventSelectedListener: OnEventEventListener? = null

    fun setEvents(events: List<Event>) {
        this.events.clear()
        this.events.addAll(events)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
        onEventSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onEventSelected(event.id)}
            holder.itemView.setOnLongClickListener { listener.onEventLongPress(event)}
        }
    }

    override fun getItemCount(): Int {
        return events.count()
    }
}