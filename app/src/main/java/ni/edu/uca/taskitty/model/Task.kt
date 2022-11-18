package ni.edu.uca.taskitty.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "tblTask")
open class Task (
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description : String,
    @ColumnInfo(name = "fixed") var fixed: Boolean,
    @ColumnInfo(name = "color") var color : Int
    )
