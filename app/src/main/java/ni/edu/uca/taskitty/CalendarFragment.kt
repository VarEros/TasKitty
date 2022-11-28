package ni.edu.uca.taskitty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.adapter.EventMinimalRecycler
import ni.edu.uca.taskitty.adapter.EventRecycler
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent
import ni.edu.uca.taskitty.databinding.FragmentCalendarBinding
import ni.edu.uca.taskitty.databinding.FragmentNewEventBinding
import ni.edu.uca.taskitty.model.Event


class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private var eventList : MutableList<Event> = mutableListOf()
    private lateinit var daoEvent: DaoEvent
    private lateinit var rcvComingEvent : RecyclerView

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
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCalendar()
        establecerAdapter()
    }

    private fun establecerAdapter(){
        rcvComingEvent = binding.rvComingEvents
        rcvComingEvent.layoutManager = LinearLayoutManager(binding.root.context)
        rcvComingEvent.adapter = EventMinimalRecycler(binding.root.context,eventList,0, {event -> onClickEvent(event)})

    }

    private fun onClickEvent(event : Event){
        val dialog = EventViewDialog(event)
        dialog.show(parentFragmentManager,"custom")
    }

    private fun setUpCalendar() {
        binding.calendarView.setOnDateChangeListener(
                OnDateChangeListener { view, year, month, dayOfMonth ->
                    val Date = (dayOfMonth.toString() + "-"
                            + (month + 1) + "-" + year)
                    binding.idTVDate.setText(Date)
                    })

    }
}
