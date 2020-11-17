package ussrfantom.com.example.kawaiinotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes; //наш ресайклер вью
    public static final ArrayList<Note> notes = new ArrayList<>(); //массив обьектов, наших заметок
    private NotesAdapter adapter;
    private NotesDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        dbHelper = new NotesDBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

    /*
        if (notes.isEmpty()) {
            notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 2));
            notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 1));
            notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 3));
            notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 3));
            notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 1));
        }

        for (Note note : notes){
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, note.getTitle());
            contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, note.getDescription());
            contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, note.getDayOfWeek());
            contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, note.getPriority());
            database.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues);
        }
*/
        ArrayList<Note> notesFromDB = new ArrayList<>();
        Cursor cursor = database.query(NotesContract.NotesEntry.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DESCRIPTION));
            String dayOfWeek = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK));
            int priority = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_PRIORITY));
            Note note = new Note(title, description, dayOfWeek, priority);
            notesFromDB.add(note);
        }
        cursor.close();



        adapter = new NotesAdapter(notesFromDB);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, R.string.hint_delete_note, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClock(int position) {
                remove(position);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);

    }

    private void remove (int position){
        notes.remove(position);
        adapter.notifyDataSetChanged();
    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        startActivity(intent);
    }
}