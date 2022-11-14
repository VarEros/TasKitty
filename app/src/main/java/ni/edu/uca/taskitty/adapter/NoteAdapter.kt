package ni.edu.uca.taskitty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.databinding.NoteItemBinding
import ni.edu.uca.taskitty.model.Note

class NoteAdapter(
    private var noteList: List<Note>
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private lateinit var binding: NoteItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        this.binding = NoteItemBinding.inflate(inflater, parent, false)
        return ViewHolder(this.binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList[position]
        holder.render(note)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }


    class ViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val noteTitle = binding.tvNoteTitle
        private val noteTime = binding.tvNoteTime
        private val noteDesc = binding.tvNoteDesc

        fun render(note: Note) {
            noteTitle.text = note.title
            noteTime.text = note.dateModified.toString()
            noteDesc.text = note.description
        }
    }
}