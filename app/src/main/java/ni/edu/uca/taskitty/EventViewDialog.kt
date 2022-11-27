package ni.edu.uca.taskitty

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import ni.edu.uca.taskitty.databinding.FragmentEventViewBinding
import ni.edu.uca.taskitty.model.Event
import java.lang.Exception
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoEvent


class EventViewDialog (private var event: Event): DialogFragment() {

    private lateinit var binding : FragmentEventViewBinding
    private lateinit var daoEvent: DaoEvent
    private var isDel = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDB.getInstance(requireContext().applicationContext)
        daoEvent = db.daoEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventViewBinding.inflate(inflater,container,false)
        this.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEventData()

        binding.btnEdit.setOnClickListener{
            try{
                findNavController().navigate(R.id.newEventFragment,Bundle().apply {
                    putInt("idEvent",event.idEvent)
                    putLong("dateStart", event.dateStart)
                    putLong("dateEnd", event.dateEnd)
                    putBoolean("finished", event.finished)
                    putString("title", event.title)
                    putString("description", event.description)
                    putBoolean("fixed", event.fixed)
                    putInt("color", event.color)
                })
                dismiss()
            }catch (ex : Exception){
                Toast.makeText(binding.root.context, ex.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun setEventData(){
        with(binding){
            val dateStart = DateTask(DateTask.getCalFrom(event.dateStart), requireContext(), tvEventTimeStart)
            val dateEnd = DateTask(DateTask.getCalFrom(event.dateEnd), requireContext(), tvEventTimeEnd)
            tvEventTitle.text = event.title
            tvEventDesc.text = event.description
            dateStart.setTextView()
            dateEnd.setTextView()
            eventComp.isChecked = event.finished

            when(event.color){
                1-> eventColor.setImageResource(R.drawable.circular_element_red)
                2-> eventColor.setImageResource(R.drawable.circular_element_green)
                3-> eventColor.setImageResource(R.drawable.circular_element_blue)
                4-> eventColor.setImageResource(R.drawable.circular_element_yellow)
                5-> eventColor.setImageResource(R.drawable.circular_element_purple)
                6-> eventColor.setImageResource(R.drawable.circular_element_cyan)
            }
        }
    }

    fun result() : Boolean{
        return isDel
    }

}
