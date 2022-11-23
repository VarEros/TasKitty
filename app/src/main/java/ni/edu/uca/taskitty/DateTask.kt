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
        tv.text = "${getDayOfWeek(cal.get(Calendar.DAY_OF_WEEK))} " +
                "${cal.get(Calendar.DAY_OF_MONTH)} de " +
                "${getMonth(cal.get(Calendar.MONTH))}, " +
                "${cal.get(Calendar.YEAR)} / " +
                "${cal.get(Calendar.HOUR)}:${getMinute()}" +
                cal.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.US)
    }

    fun setTvNote(){
        tv.text ="Modificado: ${getDayOfWeek(cal.get(Calendar.DAY_OF_WEEK))}, " +
                "${cal.get(Calendar.DAY_OF_MONTH)} de " +
                "${getMonth(cal.get(Calendar.MONTH))}, " +
                "${cal.get(Calendar.YEAR)} "
    }

    private fun getDayOfWeek(day: Int): String {
        return when(day) {
            2 -> "Lunes"
            3 -> "Martes"
            4 -> "Miercoles"
            5 -> "Jueves"
            6 -> "Viernes"
            7 -> "Sabado"
            else -> "Domingo"
        }
    }

    private fun getMonth(month: Int): String {
        return when(month) {
            0 -> "Enero"
            1 -> "Febrero"
            2 -> "Marzo"
            3 -> "Abril"
            4 -> "Mayo"
            5 -> "Junio"
            6 -> "Julio"
            7 -> "Agosto"
            8 -> "Septiembre"
            9 -> "Octubre"
            10 -> "Noviembre"
            else -> "Diciembre"
        }
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
                "Ha pasado"
            else if (inMinute in 1..60)
                "$inMinute minutos"
            else if (inHour in 1..23)
                "$inHour horas"
            else
                "$inDays dias"
        }

        fun getEventShortDate(long: Long): String {
            val timeLeft = long - Calendar.getInstance().timeInMillis
            val inMinute = TimeUnit.MILLISECONDS.toMinutes(timeLeft)
            val inHour = TimeUnit.MILLISECONDS.toHours(timeLeft)
            val inDays = TimeUnit.MILLISECONDS.toDays(timeLeft)

            return if(timeLeft <=0)
                "pas"
            else if (inMinute in 1..60)
                "$inMinute min"
            else if (inHour in 1..23)
                "$inHour horas"
            else
                "$inDays dias"
        }
    }

}