package ussrfantom.com.example.kawaiinotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes; //наш ресайклер вью
    public static final ArrayList<Note> notes = new ArrayList<>(); //массив обьектов, наших заметок

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        if (notes.isEmpty()) {
            notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 2));
            notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 1));
            notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 3));
            notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 3));
            notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 1));
        }
        NotesAdapter adapter = new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, "Номер позиции " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        startActivity(intent);
    }
}