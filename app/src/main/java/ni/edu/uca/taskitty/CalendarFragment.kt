package ni.edu.uca.taskitty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView.OnDateChangeListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ni.edu.uca.taskitty.adapter.EventMinimalRecycler
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent
import ni.edu.uca.taskitty.databinding.FragmentCalendarBinding
import ni.edu.uca.taskitty.model.Event
import java.util.*


class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private lateinit var eventList : List<Event>
    private var eventListDay: MutableList<Event> = mutableListOf()
    private lateinit var daoEvent: DaoEvent
    private lateinit var rcvComingEvent : RecyclerView
    private var dateSelected =  Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDB.getInstance(requireContext().applicationContext)
        daoEvent = db.daoEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        refreshDatabase()
        setEvents()
        setTv()
        setAdapter()
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarView.setOnDateChangeListener(
            OnDateChangeListener { view, year, month, dayOfMonth ->
                dateSelected.set(year, month, dayOfMonth)
                eventListDay.clear()
                setEvents()
                setTv()
                setAdapter()
            })
        refreshDatabase()
    }

    private fun setTv() {
        binding.tVDate.text = DateTask.getMidDate(dateSelected)
    }

    private fun setAdapter(){
        rcvComingEvent = binding.rvComingEvents
        rcvComingEvent.layoutManager = LinearLayoutManager(binding.root.context)
        rcvComingEvent.adapter = EventMinimalRecycler(binding.root.context,eventListDay, 0, {event -> onClickEvent(event)})
    }

    private fun onClickEvent(event : Event){
        val dialog = EventViewDialog(event)
        dialog.show(parentFragmentManager,"custom")
    }

    private fun setEvents() {
        for(event in eventList){
            if(DateTask.isThisDay(event.dateStart, dateSelected))
                eventListDay.add(event)
        }
    }

    private fun refreshDatabase() {
        GlobalScope.launch {
            eventList = daoEvent.getAll()
        }
    }
}
