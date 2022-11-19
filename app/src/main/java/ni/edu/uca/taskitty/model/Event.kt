package ni.edu.uca.taskitty.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "tblEvent")
class Event(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idEvent") val idEvent: Int = 0,
    @ColumnInfo(name = "dateStart") var dateStart: String,
    @ColumnInfo(name = "dateEnd") var dateEnd: String,
    @ColumnInfo(name = "finished") var finished: Boolean,

    title: String,
    description: String,
    fixed: Boolean,
    color: Int
): Task (title, description, fixed, color)