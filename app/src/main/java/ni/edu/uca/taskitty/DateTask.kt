package ni.edu.uca.taskitty

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import java.util.*
import java.util.concurrent.TimeUnit

class DateTask(private val cal: Calendar, val mcontext: Context, var tv: TextView): DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private var savedDay = 0
    private var savedMonth = 0
    private var savedYear = 0

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
        cal.set(savedYear,savedMonth,savedDay,hour,minute)
        setTextView()
    }

    fun setTextView(){
        tv.text = "${cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)} " +
                "${cal.get(Calendar.DAY_OF_MONTH)} of " +
                "${cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US)}, " +
                "${cal.get(Calendar.YEAR)} / " +
                "${cal.get(Calendar.HOUR)}:${getMinute()}" +
                cal.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.US)
    }

    private fun getMinute():String {
        if (cal.get(Calendar.MINUTE)<10)
            return "0${cal.get(Calendar.MINUTE)}"
        return cal.get(Calendar.MINUTE).toString()
    }

    fun getCal(): Calendar {
        return cal
    }


    companion object {
        fun getCalFrom(dateLong: Long): Calendar {
            var calType = Calendar.getInstance()
            calType.timeInMillis = dateLong
            return  calType
        }



        fun getEventDateTitle(long: Long): String {
            val timeLeft = long - Calendar.getInstance().timeInMillis
            val inMinute = TimeUnit.MILLISECONDS.toMinutes(timeLeft)
            val inHour = TimeUnit.MILLISECONDS.toHours(timeLeft)
            val inDays = TimeUnit.MILLISECONDS.toDays(timeLeft)

            return if(timeLeft <=0)
                "Passed"
            else if (inMinute in 1..60)
                "$inMinute minutes"
            else if (inHour in 1..23)
                "$inHour hours"
            else
                "$inDays days"
        }
    }

}