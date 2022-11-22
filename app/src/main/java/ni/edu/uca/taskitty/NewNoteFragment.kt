package ni.edu.uca.taskitty

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.taskitty.data.DaoNote
import ni.edu.uca.taskitty.databinding.FragmentHomeBinding
import ni.edu.uca.taskitty.databinding.FragmentNewNoteBinding

class NewNoteFragment : Fragment() {

    private lateinit var binding : FragmentNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}