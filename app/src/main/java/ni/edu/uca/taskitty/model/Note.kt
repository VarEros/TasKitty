package ni.edu.uca.taskitty.model

import java.util.*

class Note(
    private var idNote: Int,

    title: String,
    description: String,
    fixed: Boolean,
    dateModified: Date,
    color: String
): Task (title, description, fixed, dateModified, color)