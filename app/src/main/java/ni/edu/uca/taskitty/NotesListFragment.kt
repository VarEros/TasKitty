package ni.edu.uca.taskitty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.adapter.NoteRecycler
import ni.edu.uca.taskitty.databinding.FragmentNotesListBinding
import ni.edu.uca.taskitty.model.Note
import java.util.*

class NotesListFragment : Fragment() {

    private lateinit var binding : FragmentNotesListBinding
    private var noteList: MutableList<Note> = mutableListOf()
    private lateinit var recyclerNote : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteList.add(Note(1, Calendar.getInstance(),"Sacar al gato xd","Me gustan los gatos",false,1))
        noteList.add(Note(1, Calendar.getInstance(),"","Me gustan los gatos",false,2))
        noteList.add(Note(1, Calendar.getInstance(),"Sacar al gato xd","Me gustan los gatos",false,3))
        noteList.add(Note(1, Calendar.getInstance(),"Sacar al gato xd","Me gustan los gatos",false,4))
        noteList.add(Note(1, Calendar.getInstance(),"Sacar al gato xd","Me gustan los gatos",false,5))
        noteList.add(Note(1, Calendar.getInstance(),"Sacar al gato xd","Me gustan los gatos",false,6))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        establecerAdapter()
    }

    private fun establecerAdapter(){
        recyclerNote = binding.rcvNotes
        recyclerNote.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerNote.adapter = NoteRecycler(binding.root.context,noteList,0)
    }

}