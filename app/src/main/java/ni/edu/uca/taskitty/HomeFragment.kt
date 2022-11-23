package ni.edu.uca.taskitty
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.adapter.EventMinimalRecycler
import ni.edu.uca.taskitty.adapter.EventRecycler
import ni.edu.uca.taskitty.adapter.NoteRecycler
import ni.edu.uca.taskitty.databinding.FragmentHomeBinding
import ni.edu.uca.taskitty.model.Event
import ni.edu.uca.taskitty.model.Note



class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var eventList : MutableList<Event> = mutableListOf()
    private var noteList : MutableList<Note> = mutableListOf()
    private lateinit var recyclerNormal : RecyclerView
    private lateinit var recyclerEvents : RecyclerView
    private lateinit var recyclerNotes : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnAllEvents.setOnClickListener {

                findNavController().navigate(R.id.eventListFragment)
            }
            btnAllNotes.setOnClickListener {

                findNavController().navigate(R.id.notesListFragment)
            }
            btnCalendar.setOnClickListener{
                findNavController().navigate(R.id.calendarFragment)
            }
        }

        establecerEventAdapter()
    }

    private fun establecerEventAdapter(){
        recyclerNormal = binding.rcvEventFix
        recyclerNormal.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerNormal.adapter = EventRecycler(binding.root.context, eventList,2,{event -> onClickEvent(event)})

        recyclerEvents = binding.rcvEvents
        recyclerEvents.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerEvents.adapter = EventMinimalRecycler(binding.root.context,eventList,0)

        recyclerNotes = binding.rcvNotes
        recyclerNotes.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerNotes.adapter = NoteRecycler(binding.root.context,noteList,1, {note -> onClickNote(note)})

    }

    private fun onClickNote(note: Note) {

    }

    private fun onClickEvent(event: Event) {

    }
}