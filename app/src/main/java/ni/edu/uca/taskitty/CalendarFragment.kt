package ni.edu.uca.taskitty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView.OnDateChangeListener
import androidx.fragment.app.Fragment
import ni.edu.uca.taskitty.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCalendarBinding.inflate(layoutInflater)
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
