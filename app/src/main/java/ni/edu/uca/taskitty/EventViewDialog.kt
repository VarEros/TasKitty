package ni.edu.uca.taskitty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ni.edu.uca.taskitty.databinding.FragmentEventViewBinding

class EventViewDialog : DialogFragment() {

    lateinit var binding : FragmentEventViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventViewBinding.inflate(inflater,container,false)
        return binding.root
    }

}