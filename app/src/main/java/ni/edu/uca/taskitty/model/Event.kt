package ni.edu.uca.taskitty.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import java.util.Calendar

@Entity(tableName = "tblEvent")
class Event(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idEvent") var idEvent: Int = 0,
    @ColumnInfo(name = "dateStart") var dateStart: Long,
    @ColumnInfo(name = "dateEnd") var dateEnd: Long,
    @ColumnInfo(name = "finished") var finished: Boolean,

    title: String,
    description: String,
    fixed: Boolean,
    color: Int
): Task (title, description, fixed, color) {
    fun setId(id: Int) {
        idEvent = id
    }
}