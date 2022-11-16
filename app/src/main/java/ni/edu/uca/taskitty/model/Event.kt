package ni.edu.uca.taskitty.model

import java.util.*

class Event(
    var idEvent: Int,
    var dateStart: Calendar,
    var dateEnd: Calendar,
    var finished: Boolean,

    title: String,
    description: String,
    fixed: Boolean,
    color: Int
): Task (title, description, fixed, color)