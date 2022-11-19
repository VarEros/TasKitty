package ni.edu.uca.taskitty.data

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import ni.edu.uca.taskitty.NewEventFragment
import ni.edu.uca.taskitty.model.Event

@Database(entities = [Event::class], version = 1)
abstract class AppDB: RoomDatabase() {
    abstract fun daoEvent(): DaoEvent

    companion object {
        private var instance: AppDB? = null
        private const val TASKITTY_DATABASE_NAME = "DbTaskitty"

        fun getInstance(context: Context): AppDB {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, AppDB::class.java, TASKITTY_DATABASE_NAME).build()
                }
            return instance!!
        }
    }
}