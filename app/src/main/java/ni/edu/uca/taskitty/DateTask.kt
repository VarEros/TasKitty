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
        tv.setText("${cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)} $savedDay of ${cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US)}, $savedYear / $savedHour:$savedMinute${cal.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.US)}")
    }

}