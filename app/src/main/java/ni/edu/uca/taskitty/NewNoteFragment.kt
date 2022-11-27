package ni.edu.uca.taskitty

import android.app.AlertDialog
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
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

    private fun showAlert(titleText : String, bodyText : String){
        val eBuilder = AlertDialog.Builder(binding.root.context)
        eBuilder.setTitle(titleText)
        eBuilder.setIcon(R.drawable.ic_warning)
        eBuilder.setMessage(bodyText)
        eBuilder.setPositiveButton("Si"){
                Dialog,which->
            requireActivity().onBackPressed()
        }
        eBuilder.setNegativeButton("No"){
                Dialog,which->
        }
        eBuilder.create().show()
    }

    private fun showAlertElim(titleText : String, bodyText : String){
        val eBuilder = AlertDialog.Builder(binding.root.context)
        eBuilder.setTitle(titleText)
        eBuilder.setIcon(R.drawable.ic_warning)
        eBuilder.setMessage(bodyText)
        eBuilder.setPositiveButton("Si"){
                Dialog,which->
            requireActivity().onBackPressed()
            deleteNote()
        }
        eBuilder.setNegativeButton("No"){
                Dialog,which->
        }
        eBuilder.create().show()
    }

    private fun setupOnBackPressed(){
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true){
            override fun handleOnBackPressed(){
                if(safeSave){
                    isEnabled = false
                    activity?.onBackPressed()
                }

                if(isEnabled){
                    showAlert("Salir de crear evento","¿Deseas salir de eventos sin guardar cambios?")
                    isEnabled = false
                }
            }
        })
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
            setupOnBackPressed()
            Toast.makeText(binding.root.context, "Evento descartado", Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }

        binding.btnDelet.setOnClickListener {

            showAlertElim("Eliminar nota", "¿Deseas eliminar esta nota?")

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
            if (etTitleda.text.toString().isEmpty()) {
                Toast.makeText(context, "El apartado de Descripción es requerido", Toast.LENGTH_SHORT).show()
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
        if (!editMode) {
            GlobalScope.launch {
                daoNote.insert(newNote)
            }
            Toast.makeText(context, "Nota Agregada", Toast.LENGTH_SHORT).show()
        } else {
            GlobalScope.launch {
                newNote.setId(idNote)
                daoNote.update(newNote)
            }
            Toast.makeText(context, "Cambios Guardados", Toast.LENGTH_SHORT).show()
        }
        safeSave = true
        activity?.onBackPressed()
    }

    private fun deleteNote(){
        GlobalScope.launch { daoNote.delete(idNote) }
        Toast.makeText(context, "Eliminada", Toast.LENGTH_SHORT).show()
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