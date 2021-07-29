package com.taknikinigga.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Delete
    suspend fun delete(notes: Notes)

    @Query("Select * From notes_table order by id ASC")
    fun getAllNoteList(): LiveData<List<Notes>>

}