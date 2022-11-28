package ni.edu.uca.taskitty.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.ColorTask
import ni.edu.uca.taskitty.DateTask
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.model.Note


class NoteRecycler(var context : Context, var noteList: MutableList<Note>, var mode : Int, private val onClickNote : (Note) -> Unit):
    RecyclerView.Adapter<NoteRecycler.NoteHolder>() {



    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
             var noteTitle : TextView
             var noteDesc : Button
             var noteDate : TextView
             var noteColor : ConstraintLayout
             var noteFix : ImageView

            init {
                noteTitle = itemView.findViewById(R.id.tvNoteTitle)
                noteDesc = itemView.findViewById(R.id.btnDesc)
                noteDate = itemView.findViewById(R.id.tvNoteTime)
                noteColor = itemView.findViewById(R.id.constNoteColor)
                noteFix = itemView.findViewById(R.id.starFixNote)


                when(mode){
                    1->{
                        noteDate.visibility = View.GONE
                        noteDesc.background = ActivityCompat.getDrawable(context,R.drawable.note_element_mid_rounded)
                    }
                }

            }

        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        var itemView =  LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        var note = noteList[position]
        val dateModified = DateTask(DateTask.getCalFrom(note.dateModified), context,holder.noteDate)
        holder.noteTitle.text = note.title
        if(note.description.length > 250)
            holder.noteDesc.text = note.description.substring(0,250) + "..."
        else
            holder.noteDesc.text = note.description
        ColorTask.setColorBack(note.color, holder.noteColor)
        dateModified.setTvNote()

        if(note.title == "" || note.title.isBlank()){
            holder.noteTitle.text = "Anotación sin título"
            holder.noteTitle.setTextColor(Color.parseColor("#6D000000"))
        }

        holder.noteDesc.setOnClickListener{
            onClickNote(note)
        }

        if(!note.fixed)
            holder.noteFix.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return noteList.size
    }


}