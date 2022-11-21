package ni.edu.uca.taskitty

import android.os.Bundle
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.databinding.FragmentCalendarBinding


class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    lateinit var dateTV: TextView
    lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        val view = binding.root
        setUpCalendar()



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
