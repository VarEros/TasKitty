package ni.edu.uca.taskitty.model

import java.util.*

class Note(
    private var idNote: Int,
    var dateModified: Calendar,

    title: String,
    description: String,
    fixed: Boolean,
    color: Int
): Task (title, description, fixed, color)