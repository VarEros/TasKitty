package ni.edu.uca.taskitty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.ColorTask
import ni.edu.uca.taskitty.DateTask
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.model.Event

class EventMinimalRecycler(var context : Context, var eventsList: MutableList<Event>, var mode : Int):
    RecyclerView.Adapter<EventMinimalRecycler.EventMinimalHolder>() {

        inner class EventMinimalHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
            var eventTitle : TextView
            var eventDate : TextView
            lateinit var eventColor : ImageView

            init {
                eventTitle = itemView.findViewById(R.id.tvEventTitle)
                eventDate = itemView.findViewById(R.id.tvLeftTime)
                eventColor = itemView.findViewById(R.id.eventColor)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventMinimalHolder {
        var itemView =  LayoutInflater.from(context).inflate(R.layout.event_item, parent, false)
        return EventMinimalHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventMinimalHolder, position: Int) {
        var event = eventsList[position]
        holder.eventTitle.text = event.title
        ColorTask.setColorCircle(event.color, holder.eventColor)
        holder.eventDate.text = DateTask.getEventShortDate(event.dateStart)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

}

/*
    rojo -> 1
    verde -> 2
    azul -> 3
    amarillo -> 4
    morado -> 5
    cian -> 6
* */