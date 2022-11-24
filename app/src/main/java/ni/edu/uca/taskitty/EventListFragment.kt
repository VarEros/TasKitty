package ni.edu.uca.taskitty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ni.edu.uca.taskitty.adapter.EventRecycler
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent
import ni.edu.uca.taskitty.databinding.FragmentEventListBinding
import ni.edu.uca.taskitty.model.Event

class EventListFragment : Fragment() {

    private lateinit var binding: FragmentEventListBinding
    private  lateinit var daoEvent: DaoEvent

    private var eventList : MutableList<Event> = mutableListOf()
    private var eventListNormal : MutableList<Event> = mutableListOf()
    private var eventListCompleted : MutableList<Event> = mutableListOf()

    private lateinit var recyclerNormal :  RecyclerView
    private lateinit var recyclerCompleted :  RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDB.getInstance(requireContext().applicationContext)
        daoEvent = db.daoEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        refreshDataBase()
        establecerEventAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddEvent.setOnClickListener {
            findNavController().navigate(R.id.newEventFragment)
        }
        refreshDataBase()
        establecerEventAdapter()
    }

    private fun establecerEventAdapter(){
        recyclerNormal = binding.rcvEvents
        recyclerNormal.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerNormal.adapter = EventRecycler(binding.root.context, eventListNormal,1, {event -> onClickEvent(event)})

        recyclerCompleted = binding.rcvEventsComp
        recyclerCompleted.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerCompleted.adapter = EventRecycler(binding.root.context, eventListCompleted,3,{event -> onClickEvent(event)})
    }

    private fun updateRecycler(){
        recyclerNormal.adapter = EventRecycler(binding.root.context, eventListNormal,1, {event -> onClickEvent(event)})
        recyclerCompleted.adapter = EventRecycler(binding.root.context, eventListCompleted,3,{event -> onClickEvent(event)})
    }

    fun refreshDataBase(){
        GlobalScope.launch {
            eventList = daoEvent.getAll().toMutableList()
        }
        filtrateElements()
    }

    private fun filtrateElements(){
        var eventC = mutableListOf<Event>()
        var eventN = mutableListOf<Event>()

        for(event in eventList){
            if(event.finished)
                eventC.add(event)
            else
                eventN.add(event)
        }
        eventListNormal = eventN
        eventList = mutableListOf()

        if(eventC.isEmpty()){
            if (binding.eventCompSep.visibility == View.VISIBLE) {
                binding.eventCompSep.visibility = View.INVISIBLE
                binding.rcvEventsComp.visibility = View.INVISIBLE
                return
            }
        }
        if (binding.eventCompSep.visibility == View.INVISIBLE) {
            binding.eventCompSep.visibility = View.VISIBLE
            binding.rcvEventsComp.visibility = View.VISIBLE
        }
        eventListCompleted = eventC
    }

    private fun onClickEvent(event : Event){
        val dialog = EventViewDialog(event)
        dialog.show(parentFragmentManager,"custom")
    }

}