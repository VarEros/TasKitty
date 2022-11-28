package ni.edu.uca.taskitty.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ONG (
      var logo: Int,
      var title: String,
      var description: String,
      var wDonar: String
        )