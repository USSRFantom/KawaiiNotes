package ussrfantom.com.example.kawaiinotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes; //наш ресайклер вью
    private ArrayList<Note> notes = new ArrayList<>(); //массив обьектов, наших заметок

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 2));
        notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 1));
        notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 3));
        notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 3));
        notes.add(new Note("парикмахер", "сделать прическу", "Понедельник", 1));
        NotesAdapter adapter = new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);

    }
}