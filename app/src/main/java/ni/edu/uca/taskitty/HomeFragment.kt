package ni.edu.uca.taskitty
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import ni.edu.uca.taskitty.databinding.EventElementBinding
import ni.edu.uca.taskitty.databinding.FragmentHomeBinding
import ni.edu.uca.taskitty.model.Event
import ni.edu.uca.taskitty.model.Note
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private var eventList : MutableList<Event> = mutableListOf()
    private var noteList : MutableList<Note> = mutableListOf()

    private lateinit var recyclerEvents : RecyclerView
    private lateinit var recyclerNotes : RecyclerView

    private  lateinit var daoEvent: DaoEvent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDB.getInstance(requireContext().applicationContext)
        daoEvent = db.daoEvent()
        refreshDataBase()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        formData()

        with(binding) {
            btnAllEvents.setOnClickListener {
                findNavController().navigate(R.id.eventListFragment)
            }
            btnAllNotes.setOnClickListener {
                findNavController().navigate(R.id.notesListFragment)
            }
        }
    }

    private fun formData(){
        setEvents()
        recyclerNotes = binding.rcvNotes
        recyclerNotes.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerNotes.adapter = NoteRecycler(binding.root.context,noteList,1, {note -> onClickNote(note)})
    }

    private fun setEvents() {
        when(eventList.size) {
            0 -> {
                establecerMainEvent(binding.mainEvent)
                return
            }
            1 -> {
                return
            }
        }
        establecerMainEvent(binding.mainEvent)
        recyclerEvents = binding.rcvEvents
        recyclerEvents.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerEvents.adapter = EventMinimalRecycler(binding.root.context,eventList.subList(1,eventList.size),0)
    }

    private fun establecerMainEvent(mainEvent: EventElementBinding) {
        val event = eventList[0]
        mainEvent.tvEventTitle.text = event.title
        mainEvent.tvNoteDesc.text = event.description
        ColorTask.setColorCircle(event.color ,mainEvent.eventColor)
        mainEvent.tvNoteTime.text = DateTask.getEventDateTitle(event.dateStart)
        mainEvent.eventComp.visibility = View.GONE
    }

    private fun onClickNote(note: Note) {

    }

    private fun onClickEvent(event: Event) {

    }

    fun refreshDataBase(){
        GlobalScope.launch {
            eventList = daoEvent.getAllForHome().toMutableList()
        }
    }
}