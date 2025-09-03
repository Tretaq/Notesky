package com.example.notesky.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesky.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("Select * From NoteEntity")
    fun getAllNotes():  Flow<List<NoteEntity>>

    @Query(
        """
            Select * From NoteEntity
            Where id = :id
        """
    )
    suspend fun getNoteById(id: Int): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun  insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)
}