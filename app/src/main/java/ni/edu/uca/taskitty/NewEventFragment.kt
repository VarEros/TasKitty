package ni.edu.uca.taskitty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent
import ni.edu.uca.taskitty.databinding.FragmentNewEventBinding
import ni.edu.uca.taskitty.model.Event
import java.text.SimpleDateFormat
import java.util.*

class NewEventFragment : Fragment() {
    private lateinit var binding: FragmentNewEventBinding
    private lateinit var daoEvent: DaoEvent
    private lateinit var date: DateTask

    private var calStart = Calendar.getInstance()
    private var calEnd = calStart

    private lateinit var newEvent: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDB.getInstance(requireContext().applicationContext)
        daoEvent = db.daoEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewEventBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEstabStart.setOnClickListener {
            pickDate(calStart, binding.tvStartPlace)
        }

        binding.btnEstabEnd.setOnClickListener {
            pickDate(calEnd, binding.tvEndPlace)
        }

        binding.btnAccept.setOnClickListener {
            makeEvent()
        }
    }

    private fun pickDate(cal: Calendar, tvStartPlace: TextView) {
        date = DateTask(cal, requireContext(), tvStartPlace)
        date.start()
    }


    private fun makeEvent() {

        with(binding) {
            val title = tfTitle.text.toString()
            val description = etTitleda.text.toString()
            val dateStart = SimpleDateFormat("yyyy-MM-dd").format(calStart.time)
            val dateEnd = SimpleDateFormat("yyyy-MM-dd").format(calEnd.time)
            val color = rgGroup.checkedRadioButtonId

            newEvent = Event(dateStart = dateStart, dateEnd = dateEnd, finished = false, title = title, description = description, fixed = false, color = color)
        }
        GlobalScope.launch {
            daoEvent.insert(newEvent)
            //Toast.makeText(context, "El evento se ha registrado con exito.", Toast.LENGTH_SHORT).show()
        }
    }
}