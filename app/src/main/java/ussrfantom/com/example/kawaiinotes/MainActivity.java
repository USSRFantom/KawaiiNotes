package ussrfantom.com.example.kawaiinotes;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static ussrfantom.com.example.kawaiinotes.R.string.click_click_exit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes; //наш ресайклер вью
    private final ArrayList<Note> notes = new ArrayList<>(); //массив обьектов, наших заметок
    private NotesAdapter adapter;
    private MainViewModel viewModel;
    String[] texts;
    Dialog dialog;
    private long backPressedTime;
    private Toast backToast;
    public static String Monday;
    public static String Tuesday;
    public static String Wednesday;
    public static String Thursday;
    public static String Friday;
    public static String Saturday;
    public static String Sunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Monday = getString(R.string.Monday);
        Tuesday = getString(R.string.Tuesday);
        Wednesday = getString(R.string.Wednesday);
        Thursday = getString(R.string.Thursday);
        Friday = getString(R.string.Friday);
        Saturday = getString(R.string.Saturday);
        Sunday = getString(R.string.Sunday);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        int random_number = (int) (Math.random() * 5);
        texts = getResources().getStringArray(R.array.day_info);




        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView textViewInfo = (TextView) dialog.findViewById(R.id.textViewInfoTrue);
        textViewInfo.setText(texts[random_number]);

        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                }catch (Exception e){

                }
            }
        });


        Button buttoncontinue = dialog.findViewById(R.id.buttoncontinue);
        buttoncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                }catch (Exception e){

                }
            }
        });



        dialog.show();


        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        adapter = new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        getData();
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
        Note note = adapter.getNotes().get(position);
        viewModel.deleteNote(note);
    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        startActivity(intent);
    }

    private void getData(){
        LiveData<List<Note>> notesFromDB = viewModel.getNotes(); //получили заметки
        notesFromDB.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notesFromLiveData) {
                adapter.setNotes(notesFromLiveData);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(this, click_click_exit, Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    public void onClickAlarm(View view) {
        Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
        startActivity(intent);
    }
}