package ni.edu.uca.taskitty

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ni.edu.uca.taskitty.adapter.EventRecycler
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent
import ni.edu.uca.taskitty.databinding.FragmentEventListBinding
import ni.edu.uca.taskitty.model.Event
import java.lang.Exception

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
        refreshDataBase()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        refreshDataBase()
        establecerEventAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddEvent.setOnClickListener {
            findNavController().navigate(R.id.newEventFragment)
        }
        binding.btnUpdate.setOnClickListener {
            refreshDataBase()
            establecerEventAdapter()
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
        Toast.makeText(context, "Actualizando...", Toast.LENGTH_SHORT).show()
    }

    private fun updateRecycler(){
        recyclerNormal.adapter = EventRecycler(binding.root.context, eventListNormal,1, {event -> onClickEvent(event)})
        recyclerCompleted.adapter = EventRecycler(binding.root.context, eventListCompleted,3,{event -> onClickEvent(event)})
    }

    fun refreshDataBase(){
        val db = AppDB.getInstance(requireContext().applicationContext)
        daoEvent = db.daoEvent()
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
        eventListCompleted = eventC
        eventListNormal = eventN
        eventList = mutableListOf()
    }

    private fun onClickEvent(event : Event){
        val dialog = EventViewDialog(event)
        dialog.show(parentFragmentManager,"custom")
    }

}