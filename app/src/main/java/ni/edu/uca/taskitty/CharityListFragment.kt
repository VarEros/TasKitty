package ni.edu.uca.taskitty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.taskitty.databinding.FragmentCharityListBinding

class CharityListFragment : Fragment() {

    private lateinit var binding : FragmentCharityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharityListBinding.inflate(inflater,container,false)
        return binding.root
    }

}