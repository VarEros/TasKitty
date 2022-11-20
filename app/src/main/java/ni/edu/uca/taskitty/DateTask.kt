package ni.edu.uca.taskitty

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import ni.edu.uca.taskitty.databinding.FragmentNewEventBinding
import java.lang.Exception
import java.util.*
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class DateTask(private val cal: Calendar, val mcontext: Context, var tv: TextView): DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private var savedDay = 0
    private var savedMonth = 0
    private var savedYear = 0
    private var savedHour = 0
    private var savedMinute = 0

    fun start() {
            DatePickerDialog(mcontext, this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        TimePickerDialog(mcontext, this, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true).show()
    }

    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
        savedHour = hour
        savedMinute = minute

        cal.set(savedYear,savedMonth,savedDay,savedHour,savedMinute)
        tv.text = setTextView()
    }

    fun setTextView(): String{
        return "${cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)} ${cal.get(Calendar.DAY_OF_MONTH)} of ${cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US)}, ${cal.get(Calendar.YEAR)} / ${cal.get(Calendar.HOUR)}:${getMinute()}${cal.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.US)}"
    }

    private fun getMinute():String {
        if (cal.get(Calendar.MINUTE)<10)
            return "0${cal.get(Calendar.MINUTE)}"
        return cal.get(Calendar.MINUTE).toString()
    }

    fun getCal(): Calendar {
        return cal
    }

    fun getEventDateTitle(): String {
        val timeLeft = cal.time.compareTo(Calendar.getInstance().time)
        return if (timeLeft.minutes.toString().toInt() in 1..59)
            "${timeLeft.minutes.toString().toInt()} minutes"
        else if (timeLeft.hours.toString().toInt() in 1..23)
            "${timeLeft.hours.toString().toInt()} hours"
        else
            "${timeLeft.days.toString().toInt()} days"
    }

}