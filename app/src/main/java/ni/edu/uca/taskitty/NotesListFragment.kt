package ni.edu.uca.taskitty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ni.edu.uca.taskitty.adapter.NoteRecycler
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent
import ni.edu.uca.taskitty.data.DaoNote
import ni.edu.uca.taskitty.databinding.FragmentNotesListBinding
import ni.edu.uca.taskitty.model.Event
import ni.edu.uca.taskitty.model.Note
import java.util.*

class NotesListFragment : Fragment() {

    private lateinit var binding : FragmentNotesListBinding
    private var noteList: MutableList<Note> = mutableListOf()
    private lateinit var recyclerNote : RecyclerView
    private  lateinit var daoNote: DaoNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        refreshDataBase()
        establecerAdapter()
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.newNoteFragment, Bundle().apply {
                putInt("idNote", 0)
            })
        }
        refreshDataBase()
        establecerAdapter()
    }

    private fun establecerAdapter(){
        recyclerNote = binding.rcvNotes
        recyclerNote.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerNote.adapter = NoteRecycler(binding.root.context,noteList,0,{note -> onClickNote(note)})
    }

    private fun onClickNote(note: Note) {
        val dialog = NoteViewDialog(note)
        dialog.show(parentFragmentManager,"custom")
    }

    fun refreshDataBase(){
        val db = AppDB.getInstance(requireContext().applicationContext)
        daoNote = db.daoNote()
        GlobalScope.launch {
            noteList = daoNote.getAll().toMutableList()
        }
    }

}