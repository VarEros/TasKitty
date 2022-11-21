package ni.edu.uca.taskitty

import android.content.Intent
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
import android.app.Fragment
import androidx.navigation.NavDirections
import ni.edu.uca.taskitty.databinding.FragmentNewEventBinding


class EventViewDialog (private var event: Event): DialogFragment() {

    private lateinit var binding : FragmentEventViewBinding
    val storedVerificationId:String = "verificationId"

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
                })
                dismiss()
            }catch (ex : Exception){
                Toast.makeText(binding.root.context, "${ex.toString()}", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnDelet.setOnClickListener {
            dismiss()
        }
    }

    private fun setEventData(){
        binding.tvEventTitle.text = event.title
        binding.tvEventDesc.text = event.description
        binding.tvEventTime.text = event.dateEnd.toString()
    }

}
