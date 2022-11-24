package ni.edu.uca.taskitty.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ni.edu.uca.taskitty.model.Event

@Dao
interface DaoEvent {
    @Query("SELECT * FROM tblEvent ORDER BY dateStart ASC")
    suspend fun getAll(): List<Event>

    @Query("SELECT * FROM tblEvent WHERE (finished='0') ORDER BY dateStart ASC limit 4")
    suspend fun getAllForHome(): List<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Query("DELETE FROM tblEvent WHERE idEvent =:id")
    suspend fun delete(id: Int)
}