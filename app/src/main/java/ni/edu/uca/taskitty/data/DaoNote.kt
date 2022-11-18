package ni.edu.uca.taskitty.data

import androidx.room.*
import ni.edu.uca.taskitty.model.Note

@Dao
interface DaoNote {
    @Query("SELECT * FROM tblNote")
    suspend fun getAll(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Note)

    @Update
    suspend fun update(event: Note)

    @Query("DELETE FROM tblNote WHERE idNote =:id")
    suspend fun delete(id:Int)
}