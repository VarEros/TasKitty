package ni.edu.uca.taskitty.adapter

import android.content.Context
import java.text.SimpleDateFormat
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.DateTask
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.model.Event
import java.util.*
import java.text.*

class EventRecycler(var context : Context, var eventsList: MutableList<Event>, var mode : Int, private val onClickEvent : (Event) -> Unit):
    RecyclerView.Adapter<EventRecycler.eventHolder>() {


    inner class eventHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var event : Event
        var eventTitle : TextView
        var eventDesc : TextView
        var eventDate : TextView
        var eventColor : ImageView
        var eventComp : CheckBox
        var btnEnter : ImageButton

        init {
            eventTitle = itemView.findViewById(R.id.tvEventTitle)
            eventDesc = itemView.findViewById(R.id.tvNoteDesc)
            eventDate = itemView.findViewById(R.id.tvNoteTime)
            eventColor = itemView.findViewById(R.id.eventColor)
            eventComp = itemView.findViewById(R.id.eventComp)
            btnEnter = itemView.findViewById(R.id.btnEnterEvent)


            when(mode){
                1->{
                    eventComp.isEnabled = false
                }
                2 ->{
                    eventTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12f)
                    eventDesc.setTextSize(TypedValue.COMPLEX_UNIT_DIP,8f)
                    eventDesc.layoutParams.height = 150
                    eventDate.setTextSize(TypedValue.COMPLEX_UNIT_DIP,8f)
                };
                3->{
                    eventDate.visibility = View.GONE
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eventHolder {
        var itemView =  LayoutInflater.from(context).inflate(R.layout.event_element, parent, false)
        return eventHolder(itemView);
    }

    override fun onBindViewHolder(holder: eventHolder, position: Int) {
        var event = eventsList[position]

        if(mode == 2 && event.title.length > 11){
            event.title = event.title.substring(0,10) + "...";
        }

        holder.eventTitle.text = event.title
        holder.eventDesc.text = event.description
        holder.eventDate.text = DateTask.getEventDateTitle(event.dateStart)
        holder.eventComp.isChecked = event.finished

        holder.eventComp.isEnabled = false

        holder.btnEnter.setOnClickListener {
            onClickEvent(event)
        }

        when(event.color){
            1-> holder.eventColor.setImageResource(R.drawable.circular_element_red)
            2-> holder.eventColor.setImageResource(R.drawable.circular_element_green)
            3-> holder.eventColor.setImageResource(R.drawable.circular_element_blue)
            4-> holder.eventColor.setImageResource(R.drawable.circular_element_yellow)
            5-> holder.eventColor.setImageResource(R.drawable.circular_element_purple)
            6-> holder.eventColor.setImageResource(R.drawable.circular_element_cian)
        }

        // adjusts
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