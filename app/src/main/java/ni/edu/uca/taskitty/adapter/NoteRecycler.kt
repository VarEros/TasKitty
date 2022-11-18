package ni.edu.uca.taskitty.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.pm.ShortcutXmlParser
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.NoteViewDialog
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.databinding.NoteItemBinding
import ni.edu.uca.taskitty.model.Event
import ni.edu.uca.taskitty.model.Note

class NoteRecycler(var context : Context, var noteList: MutableList<Note>, var mode : Int):
    RecyclerView.Adapter<NoteRecycler.NoteHolder>() {

        inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            lateinit var noteTitle : TextView
            lateinit var noteDesc : Button
            lateinit var noteDate : TextView
            lateinit var noteColor : ConstraintLayout
            lateinit var noteFix : ImageView


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
        holder.noteTitle.text = note.title
        holder.noteDesc.text = note.description
        holder.noteDate.text = note.dateModified.time.toString()


        if(note.title == "" || note.title.isBlank()){
            holder.noteTitle.text = "Anotación sin título"
            holder.noteTitle.setTextColor(Color.parseColor("#6D000000"))
        }

        if(!note.fixed)
            holder.noteFix.visibility = View.GONE

        when(note.color){
            1->holder.noteColor.setBackgroundResource(R.drawable.note_internal_red)
            2->holder.noteColor.setBackgroundResource(R.drawable.note_internal_green)
            3->holder.noteColor.setBackgroundResource(R.drawable.note_internal_blue)
            4->holder.noteColor.setBackgroundResource(R.drawable.note_internal_yellow)
            5->holder.noteColor.setBackgroundResource(R.drawable.note_internal_purple)
            6->holder.noteColor.setBackgroundResource(R.drawable.note_internal_cian)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }


}