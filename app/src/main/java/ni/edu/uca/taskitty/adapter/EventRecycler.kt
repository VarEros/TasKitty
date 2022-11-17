package ni.edu.uca.taskitty.adapter

import android.content.Context
import android.icu.text.AlphabeticIndex
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.model.Event
import java.util.*

class EventRecycler(var context : Context, var eventsList: MutableList<Event>, var mode : Int):
    RecyclerView.Adapter<EventRecycler.eventHolder>() {

    inner class eventHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var eventTitle : TextView
        var eventDesc : TextView
        var eventDate : TextView
        var eventColor : ImageView
        lateinit var eventComp : CheckBox

        init {
            eventTitle = itemView.findViewById(R.id.tvEventTitle)
            eventDesc = itemView.findViewById(R.id.tvNoteDesc)
            eventDate = itemView.findViewById(R.id.tvNoteTime)
            eventColor = itemView.findViewById(R.id.eventColor)
            eventComp = itemView.findViewById(R.id.eventComp)

            when(mode){
                1->{}
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
        holder.eventDate.text = "DataTimeDataFatCatFat"
        holder.eventComp.isChecked = event.finished

        if(event.finished)
            holder.eventComp.isEnabled = false

        when(event.color){
            1-> holder.eventColor.setImageResource(R.drawable.circular_element_red)
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