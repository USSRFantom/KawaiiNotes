package ussrfantom.com.example.kawaiinotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM notes ORDER BY dayOfWeek")
    LiveData<List<Note>> getAllNotes();  //метод получения всех заметок из бд

    @Insert
    void indertNote(Note note); //метод вставления данных

    @Delete
    void deleteNote(Note note); //метод удаления заметки

    @Query("DELETE FROM notes")
    void deleteAllNotes();
}
