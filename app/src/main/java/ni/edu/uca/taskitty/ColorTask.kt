package ni.edu.uca.taskitty

import android.widget.ImageView
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import ni.edu.uca.taskitty.databinding.FragmentNewEventBinding

class ColorTask {

    companion object {

        fun setColorCircle(color: Int, eventColor: ImageView) {
            val redCircle = R.drawable.circular_element_red
            val greenCircle = R.drawable.circular_element_green
            val blueCircle = R.drawable.circular_element_blue
            val yellowCircle = R.drawable.circular_element_yellow
            val purpleCircle = R.drawable.circular_element_purple
            val cyanCircle = R.drawable.circular_element_cyan
            val circleList = listOf(redCircle, greenCircle, blueCircle, yellowCircle, purpleCircle, cyanCircle)
            for (i in 1..6){
                if(color == i)
                    eventColor.setImageResource(circleList[i-1])
            }
        }

        fun setColorBack(color: Int, noteColor: ConstraintLayout) {
            val redBar = R.drawable.note_internal_red
            val greenBar = R.drawable.note_internal_green
            val blueBar = R.drawable.note_internal_blue
            val yellowBar = R.drawable.note_internal_yellow
            val purpleBar = R.drawable.note_internal_purple
            val cyanBar = R.drawable.note_internal_cyan
            val barList = listOf(redBar, greenBar, blueBar, yellowBar, purpleBar, cyanBar)
            for (i in 1..6) {
                if (color == i)
                    noteColor.setBackgroundResource(barList[i-1])
            }
        }
    }
}