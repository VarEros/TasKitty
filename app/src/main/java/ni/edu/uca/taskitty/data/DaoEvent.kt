package ni.edu.uca.taskitty.data

import androidx.room.*
import ni.edu.uca.taskitty.model.Event

@Dao
interface DaoEvent {
    @Query("SELECT * FROM tblEvent ORDER BY dateStart")
    suspend fun getAll(): List<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Query("DELETE FROM tblEvent WHERE idEvent =:id")
    suspend fun delete(id:Int)
}