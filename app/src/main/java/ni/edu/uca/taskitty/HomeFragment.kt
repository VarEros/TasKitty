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
import ni.edu.uca.taskitty.databinding.FragmentHomeBinding
import ni.edu.uca.taskitty.model.Event
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var eventList : MutableList<Event> = mutableListOf()
    private lateinit var recyclerNormal : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventList.add(Event(1, Calendar.getInstance(),
            Calendar.getInstance(),false,"Ponce también","No se que poner xd",false,1))
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
        }

        establecerEventAdapter()

    }

    private fun establecerEventAdapter(){
        recyclerNormal = binding.rcvEventFix
        recyclerNormal.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerNormal.adapter = EventRecycler(binding.root.context, eventList,2)

    }
}