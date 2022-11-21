package ni.edu.uca.taskitty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.processNextEventInCurrentThread
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent
import ni.edu.uca.taskitty.databinding.FragmentNewEventBinding
import ni.edu.uca.taskitty.model.Event
import java.text.SimpleDateFormat
import java.util.*

class NewEventFragment() : Fragment() {
    private lateinit var binding: FragmentNewEventBinding
    private lateinit var newEvent: Event
    private lateinit var daoEvent: DaoEvent

    private var calStart = Calendar.getInstance()
    private var calEnd = Calendar.getInstance()

    private lateinit var dateStart: DateTask
    private lateinit var dateEnd: DateTask

    private var idEvent = -1;

    private var editMode = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDB.getInstance(requireContext().applicationContext)
        daoEvent = db.daoEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDates()

        binding.btnEstabStart.setOnClickListener {
            dateStart.start()
        }

        binding.btnEstabEnd.setOnClickListener {
            dateEnd.start()

        }

        binding.btnAccept.setOnClickListener {
            makeEvent()
            activity?.onBackPressed()
        }

        binding.btnDiscard.setOnClickListener {
            activity?.onBackPressed()
        }

        idEvent = requireArguments().getInt("idEvent")
        Toast.makeText(binding.root.context, "$idEvent", Toast.LENGTH_LONG).show()
    }

    fun switchToEditMode(eventFrom : Event){
        if(eventFrom == null){
            binding.radioRed.isChecked = true;
            return
        }

        editMode = true;
        binding.topTitle.text = getString(R.string.event_edit_mode)
        binding.tfTitle.setText(eventFrom.title)
        binding.etTitleda.setText(eventFrom.description)
        binding.tvEventStart.text = eventFrom.dateStart.toString()
        binding.tvEventEnd.text = eventFrom.dateEnd.toString()

        when(eventFrom?.color){
            1-> binding.radioRed.isChecked = true;
            2-> binding.radioGreen.isChecked = true;
            3-> binding.radioBlue.isChecked = true;
            4-> binding.radioYellow.isChecked = true;
            5-> binding.radioPurple.isChecked = true;
            6-> binding.radioCian.isChecked = true;
        }
    }

    private fun setDates() {
        calStart.add(Calendar.HOUR, 1)
        calEnd.add(Calendar.HOUR, 2)
        dateStart = DateTask(calStart, requireContext(), binding.tvStartPlace)
        dateEnd = DateTask(calEnd, requireContext(), binding.tvEndPlace)
        binding.tvStartPlace.text = dateStart.setTextView()
        binding.tvEndPlace.text = dateEnd.setTextView()
    }



    private fun makeEvent() {
        calStart = dateStart.getCal()
        calEnd = dateEnd.getCal()

        with(binding) {
            if (tfTitle.text.toString().isEmpty()) {
                Toast.makeText(context, "El apartado de Titulo es requerido", Toast.LENGTH_SHORT).show()
                return
            }
            if (calStart > calEnd) {
                Toast.makeText(context, "La fecha inicial no puede ser posterior a la final", Toast.LENGTH_SHORT).show()
                return
            }

            newEvent = Event(
                dateStart = calStart.timeInMillis,
                dateEnd = calEnd.timeInMillis,
                finished = false,
                title = tfTitle.text.toString(),
                description = etTitleda.text.toString(),
                fixed = false,
                color = rgGroup.checkedRadioButtonId
            )
        }
        Toast.makeText(requireContext().applicationContext, "El evento se ha registrado con exito.", Toast.LENGTH_SHORT).show()
        GlobalScope.launch {
            daoEvent.insert(newEvent)
        }
    }
}