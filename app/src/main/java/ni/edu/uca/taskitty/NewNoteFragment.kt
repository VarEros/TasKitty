package ni.edu.uca.taskitty

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoNote
import ni.edu.uca.taskitty.databinding.FragmentNewNoteBinding
import ni.edu.uca.taskitty.model.Note
import java.util.*

class NewNoteFragment : Fragment() {

    private lateinit var binding : FragmentNewNoteBinding
    private lateinit var newNote: Note
    private lateinit var daoNote: DaoNote
    private var safeSave = false

    private var idNote = 0;
    private var editMode = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDB.getInstance(requireContext().applicationContext)
        daoNote = db.daoNote()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idNote = requireArguments().getInt("idNote")

        if (idNote ==0) {
            binding.btnDelet.visibility = View.GONE
        }
        else {
            switchToEditMode()
        }

        binding.btnAccept.setOnClickListener {
            makeNote()
        }

        binding.btnDiscard.setOnClickListener {
            Toast.makeText(binding.root.context, "Evento descartado", Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }

        binding.btnDelet.setOnClickListener {
            deleteNote()
        }

        binding.rgGroup.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = binding.rgGroup.findViewById(checkedId)
            when (radio) {
                binding.radioRed -> {
                    binding.clTop.setBackgroundResource(R.drawable.note_internal_red)
                }
                binding.radioGreen -> {
                    binding.clTop.setBackgroundResource(R.drawable.note_internal_green)
                }
                binding.radioBlue -> {
                    binding.clTop.setBackgroundResource(R.drawable.note_internal_blue)
                }
                binding.radioYellow -> {
                    binding.clTop.setBackgroundResource(R.drawable.note_internal_yellow)
                }
                binding.radioPurple -> {
                    binding.clTop.setBackgroundResource(R.drawable.note_internal_purple)
                }
                binding.radioCyan -> {
                    binding.clTop.setBackgroundResource(R.drawable.note_internal_cyan)
                }
            }
        }
    }

    fun switchToEditMode(){
        val color = requireArguments().getInt("color")
        val dateMod = DateTask(DateTask.getCalFrom(requireArguments().getLong("dateModified")), requireContext(), binding.tvLastEdit)
        
        editMode = true;
        binding.topTitle.text = getString(R.string.event_edit_mode)
        binding.tfTitle.setText(requireArguments().getString("title"))
        binding.etTitleda.setText(requireArguments().getString("description"))
        dateMod.setTvNote()
        
        ColorTask.setColorBack(color, binding.clTop)
        when(color) {
            1 -> binding.radioRed.isChecked = true
            2 -> binding.radioGreen.isChecked = true
            3 -> binding.radioBlue.isChecked = true
            4 -> binding.radioYellow.isChecked = true
            5 -> binding.radioPurple.isChecked = true
            6 -> binding.radioCyan.isChecked = true
        }
    }

    private fun makeNote() {

        with(binding) {
            if (tfTitle.text.toString().isEmpty()) {
                Toast.makeText(context, "El apartado de Titulo es requerido", Toast.LENGTH_SHORT).show()
                return
            }
            newNote = Note(
                title = tfTitle.text.toString(),
                description = etTitleda.text.toString(),
                dateModified = Calendar.getInstance().timeInMillis,
                fixed = false,
                color = getColorId()
            )
        }
        Toast.makeText(requireContext().applicationContext, "El evento se ha registrado con exito.", Toast.LENGTH_SHORT).show()
        GlobalScope.launch {
            if (!editMode)
                daoNote.insert(newNote)
            else {
                newNote.setId(idNote)
                daoNote.update(newNote)
            }
        }
        safeSave = true
        activity?.onBackPressed()
    }

    private fun deleteNote(){
        val db = AppDB.getInstance(requireContext().applicationContext)
        daoNote = db.daoNote()
        GlobalScope.launch { daoNote.delete(idNote) }
        Toast.makeText(requireContext().applicationContext, "Se ha eliminado el evento", Toast.LENGTH_SHORT).show()
        safeSave = true
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
            else if (radioCyan.isChecked)
                6
            else
                0
        }
    }

}