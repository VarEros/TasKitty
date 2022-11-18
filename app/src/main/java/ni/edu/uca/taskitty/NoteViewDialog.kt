package ni.edu.uca.taskitty

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import ni.edu.uca.taskitty.databinding.FragmentNoteViewBinding

class NoteViewDialog : DialogFragment() {

    lateinit var binding: FragmentNoteViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = FragmentNoteViewBinding.inflate(inflater,container,false)
        return binding.root
    }

}