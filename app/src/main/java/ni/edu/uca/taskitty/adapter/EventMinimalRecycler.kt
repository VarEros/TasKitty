package ni.edu.uca.taskitty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.model.Event

class EventMinimalRecycler(var context : Context, var eventsList: MutableList<Event>, var mode : Int):
    RecyclerView.Adapter<EventMinimalRecycler.EventMinimalHolder>() {

        inner class EventMinimalHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
            var eventTitle : TextView

            init {
                eventTitle = itemView.findViewById(R.id.tvEventTitle)

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventMinimalHolder {
        var itemView =  LayoutInflater.from(context).inflate(R.layout.event_item, parent, false)
        return EventMinimalHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventMinimalHolder, position: Int) {
        var event = eventsList[position]
        holder.eventTitle.text = event.title


    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

}