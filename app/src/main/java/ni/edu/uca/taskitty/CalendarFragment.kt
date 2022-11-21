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
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.databinding.FragmentCalendarBinding
import ni.edu.uca.taskitty.databinding.FragmentNewEventBinding


class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    lateinit var dateTV: TextView
    lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        setUpCalendar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setUpCalendar() {
        binding.calendarView.setOnDateChangeListener(
                OnDateChangeListener { view, year, month, dayOfMonth ->
                    val Date = (dayOfMonth.toString() + "-"
                            + (month + 1) + "-" + year)
                    dateTV.setText(Date)
                    })

    }
}
