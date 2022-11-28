package ni.edu.uca.taskitty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ni.edu.uca.taskitty.adapter.EventMinimalRecycler
import ni.edu.uca.taskitty.adapter.OngAdapter
import ni.edu.uca.taskitty.data.DataSourceONG
import ni.edu.uca.taskitty.databinding.FragmentCharityListBinding
import ni.edu.uca.taskitty.model.ONG

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        establecerAdapter()
    }

    private fun establecerAdapter(){
        with(binding.rvCharity) {
            layoutManager = LinearLayoutManager(binding.root.context)
                adapter = OngAdapter(
                binding.root.context,
                DataSourceONG.loadONG(),
                { ong -> onClickOngLink(ong) })
        }
    }

    private fun onClickOngLink(ong: ONG) {
        TODO("Not yet implemented")
    }
}