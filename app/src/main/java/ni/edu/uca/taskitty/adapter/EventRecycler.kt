package ni.edu.uca.taskitty.adapter

import android.content.Context
import android.icu.text.AlphabeticIndex
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.databinding.EventItemBinding
import ni.edu.uca.taskitty.model.Event
import java.util.*

class EventRecycler(var context : Context, var eventsList: MutableList<Event>):
    RecyclerView.Adapter<EventRecycler.eventHolder>() {

    inner class eventHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var eventTitle : TextView
        lateinit var eventDesc : TextView
        lateinit var eventDate : TextView

        init {
            eventTitle = itemView.findViewById(R.id.tvEventTitle)
            eventDesc = itemView.findViewById(R.id.tvNoteDesc)
            eventDate = itemView.findViewById(R.id.tvNoteTime)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eventHolder {
        var itemView =  LayoutInflater.from(context).inflate(R.layout.event_element, parent, false)
        return eventHolder(itemView);
    }

    override fun onBindViewHolder(holder: eventHolder, position: Int) {
        var event = eventsList[position]
        holder.eventTitle.text = event.title
        holder.eventDesc.text = event.description
        holder.eventDate.text = event.dateEnd.time.toString()

    }

    override fun getItemCount(): Int {
        return eventsList.size
    }
}