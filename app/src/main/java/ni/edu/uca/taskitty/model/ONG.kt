package ni.edu.uca.taskitty.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblOng")
data class ONG (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idOng") private var idOng: Int,
    @ColumnInfo(name = "title") private var title: String,
    @ColumnInfo(name = "description") private var description: String,
    @ColumnInfo(name = "link") private var link: String
        )