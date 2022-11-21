package ni.edu.uca.taskitty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent
import ni.edu.uca.taskitty.databinding.FragmentNewEventBinding
import ni.edu.uca.taskitty.model.Event
import java.util.*

class NewEventFragment() : Fragment() {
    private lateinit var binding: FragmentNewEventBinding
    private lateinit var newEvent: Event
    private lateinit var daoEvent: DaoEvent

    private var calStart = Calendar.getInstance()
    private var calEnd = Calendar.getInstance()

    private lateinit var dateTaskStart: DateTask
    private lateinit var dateTaskEnd: DateTask

    private var idEvent = 0;
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
        idEvent = requireArguments().getInt("idEvent")
        if (idEvent==0)
            setDates()
        else {
            switchToEditMode()
        }
        binding.btnEstabStart.setOnClickListener {
            dateTaskStart.start()
        }

        binding.btnEstabEnd.setOnClickListener {
            dateTaskEnd.start()

        }

        binding.btnAccept.setOnClickListener {
            makeEvent()
        }

        binding.btnDiscard.setOnClickListener {
            activity?.onBackPressed()
        }
        Toast.makeText(context, idEvent.toString(), Toast.LENGTH_SHORT).show()
    }

    fun switchToEditMode(){
        dateTaskStart = DateTask(DateTask.getCalFrom(requireArguments().getLong("dateStart")), requireContext(), binding.tvStartPlace)
        dateTaskEnd = DateTask(DateTask.getCalFrom(requireArguments().getLong("dateEnd")), requireContext(), binding.tvEndPlace)

        editMode = true;
        binding.topTitle.text = getString(R.string.event_edit_mode)
        binding.tfTitle.setText(requireArguments().getString("title"))
        binding.etTitleda.setText(requireArguments().getString("description"))
        dateTaskStart.setTextView()
        dateTaskEnd.setTextView()

        when(requireArguments().getInt("color")){
            1-> binding.radioRed.isChecked = true;
            2-> binding.radioGreen.isChecked = true;
            3-> binding.radioBlue.isChecked = true;
            4-> binding.radioYellow.isChecked = true;
            5-> binding.radioPurple.isChecked = true;
            6-> binding.radioCian.isChecked = true;
        }

        when(requireArguments().getInt("color")){
            1-> binding.ivEventColor.setImageResource(R.drawable.circular_element_red)
            2-> binding.ivEventColor.setImageResource(R.drawable.circular_element_green)
            3-> binding.ivEventColor.setImageResource(R.drawable.circular_element_blue)
            4-> binding.ivEventColor.setImageResource(R.drawable.circular_element_yellow)
            5-> binding.ivEventColor.setImageResource(R.drawable.circular_element_purple)
            6-> binding.ivEventColor.setImageResource(R.drawable.circular_element_cian)
        }
    }

    private fun setDates() {
        calStart.add(Calendar.HOUR, 1)
        calEnd.add(Calendar.HOUR, 2)
        dateTaskStart = DateTask(calStart, requireContext(), binding.tvStartPlace)
        dateTaskEnd = DateTask(calEnd, requireContext(), binding.tvEndPlace)
        dateTaskStart.setTextView()
        dateTaskEnd.setTextView()
    }



    private fun makeEvent() {
        calStart = dateTaskStart.getCal()
        calEnd = dateTaskEnd.getCal()

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
                color = getColorId()
            )
        }
        Toast.makeText(requireContext().applicationContext, "El evento se ha registrado con exito.", Toast.LENGTH_SHORT).show()
        GlobalScope.launch {
            if (!editMode)
                daoEvent.insert(newEvent)
            else
                newEvent.setId(idEvent)
                daoEvent.update(newEvent)
        }
        activity?.onBackPressed()
    }

    private fun getColorId(): Int {
        with(binding) {
            return if(radioRed.isChecked)
                1
            else if (radioGreen.isChecked)
                2
            else if (radioBlue.isChecked)
                3
            else if (radioYellow.isChecked)
                4
            else if (radioPurple.isChecked)
                5
            else if (radioCian.isChecked)
                6
            else
                0
        }
    }
}