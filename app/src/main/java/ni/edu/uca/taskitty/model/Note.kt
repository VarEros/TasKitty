package ni.edu.uca.taskitty.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar



@Entity(tableName = "tblNote")
class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idNote") val idNote: Int = 0,
    @ColumnInfo(name = "dateModified") var dateModified: Calendar,

    title: String,
    description: String,
    fixed: Boolean,
    color: Int
): Task (title, description, fixed, color)

