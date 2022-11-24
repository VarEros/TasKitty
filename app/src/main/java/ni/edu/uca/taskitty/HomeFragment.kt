package ni.edu.uca.taskitty
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ni.edu.uca.taskitty.adapter.EventMinimalRecycler
import ni.edu.uca.taskitty.adapter.NoteRecycler
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent
import ni.edu.uca.taskitty.data.DaoNote
import ni.edu.uca.taskitty.databinding.EventElementBinding
import ni.edu.uca.taskitty.databinding.FragmentHomeBinding
import ni.edu.uca.taskitty.model.Event
import ni.edu.uca.taskitty.model.Note


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private var eventList : MutableList<Event> = mutableListOf()
    private var noteList : MutableList<Note> = mutableListOf()

    private lateinit var recyclerEvents : RecyclerView
    private lateinit var recyclerNotes : RecyclerView

    private lateinit var daoEvent: DaoEvent
    private lateinit var daoNote: DaoNote

    private lateinit var mainEvent: Event


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDB.getInstance(requireContext().applicationContext)
        daoEvent = db.daoEvent()
        daoNote = db.daoNote()
        refreshDataBase()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onResume() {
        refreshDataBase()
        formData()
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshDataBase()
        formData()
        binding.btnAllEvents.setOnClickListener {
            findNavController().navigate(R.id.eventListFragment)
        }
        binding.btnAllNotes.setOnClickListener {
            findNavController().navigate(R.id.notesListFragment)
        }
        binding.mainEvent.btnEnterEvent.setOnClickListener {
            onClickEvent(mainEvent)
        }
    }

    private fun formData(){
        setEvents()
        setNotes()
    }

    private fun setNotes() {
        recyclerNotes = binding.rcvNotes
        recyclerNotes.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerNotes.adapter = NoteRecycler(binding.root.context,noteList,1, {note -> onClickNote(note)})
    }

    private fun setEvents() {
        when(eventList.size) {
            0 -> {
                setInvisible()
                return
            }
            1 -> {
                setVisible()
                establecerMainEvent()
                return
            }
        }
        setVisible()
        establecerMainEvent()
        recyclerEvents = binding.rcvEvents
        recyclerEvents.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerEvents.adapter = EventMinimalRecycler(binding.root.context,eventList.subList(1,eventList.size),0, {event -> onClickEvent(event)})
    }

    private fun setVisible() {
        if (binding.rcvEvents.visibility == View.GONE) {
            binding.rcvEvents.visibility = View.VISIBLE
            binding.mainEvent.frameElementEvent.visibility = View.VISIBLE
        }
    }

    private fun setInvisible() {
        if (binding.rcvEvents.visibility == View.VISIBLE) {
            binding.rcvEvents.visibility = View.GONE
            binding.mainEvent.frameElementEvent.visibility = View.GONE
        }
    }

    private fun establecerMainEvent() {
        mainEvent = eventList[0]
        binding.mainEvent.tvEventTitle.text = mainEvent.title
        binding.mainEvent.tvNoteDesc.text = mainEvent.description
            ColorTask.setColorCircle(mainEvent.color, binding.mainEvent.eventColor)
        binding.mainEvent.tvNoteTime.text = DateTask.getEventDateTitle(mainEvent.dateStart)
        binding.mainEvent.eventComp.visibility = View.GONE
    }

    private fun onClickNote(note: Note) {
        val dialog = NoteViewDialog(note)
        dialog.show(parentFragmentManager,"custom")
    }

    private fun onClickEvent(event : Event){
        val dialog = EventViewDialog(event)
        dialog.show(parentFragmentManager,"custom")
    }

    fun refreshDataBase(){
        GlobalScope.launch {
            eventList = daoEvent.getAllForHome().toMutableList()
            noteList = daoNote.getAll().toMutableList()
        }
    }
}