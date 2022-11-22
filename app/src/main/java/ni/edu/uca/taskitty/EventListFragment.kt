package ni.edu.uca.taskitty

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.fragment.NavHostFragment
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
        GlobalScope.launch {
            eventList = daoEvent.getAll().toMutableList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddEvent.setOnClickListener {
            findNavController().navigate(R.id.newEventFragment)
        }

        var finalHost = NavHostFragment.create(R.id.newEventFragment)

        checkCompletedElements()
        filtrateElements()
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

    private fun checkCompletedElements(){
        if(eventListCompleted.size < 1){
            binding.rcvEventsComp.visibility = View.GONE;
            return
        }
        binding.rcvEventsComp.visibility = View.VISIBLE;
        binding.notElements.visibility = View.INVISIBLE;
    }

    private fun filtrateElements(){
        for(event in eventList){
            if(event.finished)
                eventListCompleted.add(event)
            else
                eventListNormal.add(event)
        }
        eventList = mutableListOf()
    }

    private fun onClickEvent(event : Event){
        val dialog = EventViewDialog(event)
        dialog.show(parentFragmentManager,"custom")
    }

}