package ni.edu.uca.taskitty
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ni.edu.uca.taskitty.adapter.EventMinimalRecycler
import ni.edu.uca.taskitty.adapter.NoteRecycler
import ni.edu.uca.taskitty.data.DataSourceDYK
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent
import ni.edu.uca.taskitty.data.DaoNote
import ni.edu.uca.taskitty.databinding.FragmentHomeBinding
import ni.edu.uca.taskitty.model.DYK
import ni.edu.uca.taskitty.model.Event
import ni.edu.uca.taskitty.model.Note

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private var eventList : MutableList<Event> = mutableListOf()
    private var noteList : MutableList<Note> = mutableListOf()

    private lateinit var recyclerEvents: RecyclerView
    private lateinit var recyclerNotes: RecyclerView

    private lateinit var mainDYK: DYK
    private lateinit var mainEvent: Event

    private lateinit var daoEvent: DaoEvent
    private lateinit var daoNote: DaoNote


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

    override fun onStart() {
        mainDYK = DataSourceDYK().getRndDYK()
        loadDYK()
        super.onStart()
    }

    override fun onResume() {
        refreshDataBase()
        formData()
        super.onResume()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        refreshDataBase()
        formData()
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            includedInclude.cosntDYK.setOnClickListener{
                showDYK()
            }
            btnAllEvents.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_eventListFragment)
            }
            btnAllNotes.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_notesListFragment)
            }
        }
        binding.mainEvent.btnEnterEvent.setOnClickListener {
            onClickEvent(mainEvent)
        }
    }

    private fun loadDYK() {
        with(binding.includedInclude){
            iconIDK.setImageResource(mainDYK.icon)
            tvInfoDYK.text = mainDYK.descripcion
            bgDYK.setImageResource(mainDYK.background)
        }
    }

    private fun showDYK() {
        with(binding.includedInclude) {
            expandableDYK.visibility = if (expandableDYK.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cosntDYK, AutoTransition())
                View.GONE
            } else {
                TransitionManager.beginDelayedTransition(cosntDYK, AutoTransition())
                View.VISIBLE
            }
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
                setMainEvent()
                return
            }
        }
        setVisible()
        setMainEvent()
        recyclerEvents = binding.rcvEvents
        recyclerEvents.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerEvents.adapter = EventMinimalRecycler(binding.root.context,eventList.subList(1,eventList.size), 1, {event -> onClickEvent(event)})
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

    private fun setMainEvent() {
        mainEvent = eventList[0]
        if(mainEvent.title.length > 24)
            binding.mainEvent.tvEventTitle.text = mainEvent.title.substring(0,22) + "...";
        else
            binding.mainEvent.tvEventTitle.text = mainEvent.title
        binding.mainEvent.tvNoteDesc.text = mainEvent.description
        ColorTask.setColorCircle(mainEvent.color, binding.mainEvent.eventColor)
        binding.mainEvent.tvNoteTime.text = DateTask.getEventDateTitle(mainEvent.dateStart)
        binding.mainEvent.eventComp.visibility = View.GONE
        binding.mainEvent.tvNoteDesc.setTextSize(TypedValue.COMPLEX_UNIT_DIP,8f)
    }

    private fun onClickNote(note: Note) {
        val dialog = NoteViewDialog(note)
        dialog.show(parentFragmentManager,"custom")
    }

    private fun onClickEvent(event : Event){
        val dialog = EventViewDialog(event)
        dialog.show(parentFragmentManager,"custom")
    }

    private fun refreshDataBase(){
        GlobalScope.launch {
            eventList = daoEvent.getAllForHome().toMutableList()
            noteList = daoNote.getAllFixed().toMutableList()
        }
    }
}