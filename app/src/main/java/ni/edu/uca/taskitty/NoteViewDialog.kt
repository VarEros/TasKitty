package ni.edu.uca.taskitty

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import ni.edu.uca.taskitty.data.AppDB
import ni.edu.uca.taskitty.data.DaoNote
import ni.edu.uca.taskitty.databinding.FragmentNoteViewBinding
import ni.edu.uca.taskitty.model.Event
import ni.edu.uca.taskitty.model.Note
import java.lang.Exception

class NoteViewDialog (private var note: Note): DialogFragment() {

    lateinit var binding: FragmentNoteViewBinding
    private lateinit var daoNote: DaoNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDB.getInstance(requireContext().applicationContext)
        daoNote = db.daoNote()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = FragmentNoteViewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNoteData()

        binding.btnEdit.setOnClickListener{
            try{
                findNavController().navigate(R.id.newNoteFragment,Bundle().apply {
                    putInt("idNote",note.idNote)
                    putString("title", note.title)
                    putString("description", note.description)
                    putBoolean("fixed", note.fixed)
                    putInt("color", note.color)
                })
                dismiss()
            }catch (ex : Exception){
                Toast.makeText(binding.root.context, ex.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun setNoteData(){
        with(binding){
            val dateModif = DateTask(DateTask.getCalFrom(note.dateModified), requireContext(), tvNoteTime)
            tvNoteTitle.text = note.title
            tvDesc.text = note.description
            dateModif.setTvNote()


            when(note.color){

                1-> constNoteColor.setBackgroundResource(R.color.red_schema)
                2-> constNoteColor.setBackgroundResource(R.color.green_schema)
                3-> constNoteColor.setBackgroundResource(R.color.blue_schema)
                4-> constNoteColor.setBackgroundResource(R.color.yellow_schema)
                5-> constNoteColor.setBackgroundResource(R.color.purple_schema)
                6-> constNoteColor.setBackgroundResource(R.color.cian_schema)
            }
        }
    }

}