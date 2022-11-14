package ni.edu.uca.taskitty.model

import java.util.*

class Event(
    var idEvent: Int,
    var dateStart: Date,
    var dateEnd: Date,
    var finished: Boolean,

    title: String,
    description: String,
    fixed: Boolean,
    dateModified: Date,
    color: String
): Task (title, description, fixed, dateModified, color)