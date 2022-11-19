package ni.edu.uca.taskitty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.adapter.EventRecycler
import ni.edu.uca.taskitty.databinding.FragmentEventListBinding
import ni.edu.uca.taskitty.model.Event
import java.lang.Math.round
import java.util.*

class EventListFragment : Fragment() {

    private lateinit var binding: FragmentEventListBinding
    private var eventList : MutableList<Event> = mutableListOf()
    private var eventListNormal : MutableList<Event> = mutableListOf()
    private var eventListCompleted : MutableList<Event> = mutableListOf()
    private lateinit var recyclerNormal :  RecyclerView
    private lateinit var recyclerCompleted :  RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddEvent.setOnClickListener {
            findNavController().navigate(R.id.newEventFragment)
        }

        eventList.add(Event(1, Calendar.getInstance().toString(),Calendar.getInstance().toString(),false,"Ponce también","No se que poner xd",false,1))
        filtrateElements()
        checkCompletedElements()
        establecerEventAdapter()
    }

    private fun establecerEventAdapter(){
        recyclerNormal = binding.rcvEvents
        recyclerNormal.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerNormal.adapter = EventRecycler(binding.root.context, eventListNormal,1)
        recyclerCompleted = binding.rcvEventsComp
        recyclerCompleted.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerCompleted.adapter = EventRecycler(binding.root.context, eventListCompleted,3)
    }

    private fun checkCompletedElements(){
        if(eventListCompleted.size < 1){
            binding.rcvEventsComp.visibility = View.INVISIBLE;
            return
        }
        binding.rcvEventsComp.visibility = View.VISIBLE;
        binding.notElements.visibility = View.INVISIBLE;
    }

    private fun filtrateElements(){
        for(event in eventList){
            if(event.finished){
                eventListCompleted.add(event)
            } else{
                eventListNormal.add(event)
            }
        }
        eventList = mutableListOf();
    }

    /*
    private fun screenElementsAdapter(){
        context?.let {
            val displayMetrics = it.resources.displayMetrics
            val dpHeight = displayMetrics.heightPixels / displayMetrics.density
            val dpWidth = displayMetrics.widthPixels / displayMetrics.density
            binding.rcvEvents.height = round(dpHeight * 0.7).toInt();
        }
*/

}