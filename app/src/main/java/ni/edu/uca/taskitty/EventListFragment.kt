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
import java.util.*

class EventListFragment : Fragment() {

    private lateinit var binding: FragmentEventListBinding
    private var eventList : MutableList<Event> = mutableListOf()
    private var eventListNormal : MutableList<Event> = mutableListOf()
    private var eventListCompleted : MutableList<Event> = mutableListOf()
    private lateinit var recycler :  RecyclerView

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
        eventList.add(Event(1, Calendar.getInstance(),Calendar.getInstance(),false,"Evento prueba","No se que poner xd",false,null,"#00000000"))
        eventList.add(Event(1, Calendar.getInstance(),Calendar.getInstance(),false,"Evento prueba 2","No se que poner xdxdasdasd",false,null,"#00000000"))
        establecerEventAdapter()

    }

    private fun establecerEventAdapter(){
        recycler = binding.rcvEvents
        recycler.layoutManager = LinearLayoutManager(binding.root.context);
        recycler.adapter = EventRecycler(binding.root.context, eventList)
    }

    private fun checkCompletedElements(){
        if(eventList.size < 1){
            return
        }
        binding.notElements.visibility = View.INVISIBLE;
    }

    private fun filtrateElements(){



    }

}