package ni.edu.uca.taskitty

import android.app.AlertDialog
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    fun switchToEditMode(){
        
        editMode = true;
        binding.topTitle.text = getString(R.string.event_edit_mode)
        binding.tfTitle.setText(requireArguments().getString("title"))
        binding.etTitleda.setText(requireArguments().getString("description"))
        
        

        when(requireArguments().getInt("color")){
            1-> binding.radioRed.isChecked = true;
            2-> binding.radioGreen.isChecked = true;
            3-> binding.radioBlue.isChecked = true;
            4-> binding.radioYellow.isChecked = true;
            5-> binding.radioPurple.isChecked = true;
            6-> binding.radioCian.isChecked = true;
        }

        when(requireArguments().getInt("color")){
            1-> binding.clTop.setBackgroundResource(R.color.red_schema)
            2-> binding.clTop.setBackgroundResource(R.color.green_schema)
            3-> binding.clTop.setBackgroundResource(R.color.blue_schema)
            4-> binding.clTop.setBackgroundResource(R.color.yellow_schema)
            5-> binding.clTop.setBackgroundResource(R.color.purple_schema)
            6-> binding.clTop.setBackgroundResource(R.color.cian_schema)
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
            else if (radioCian.isChecked)
                6
            else
                0
        }
    }
}