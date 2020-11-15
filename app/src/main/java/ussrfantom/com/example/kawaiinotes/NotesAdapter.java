package ussrfantom.com.example.kawaiinotes;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
        private ArrayList<Note> notes;

    public NotesAdapter(ArrayList<Note> note) {
        this.notes = note;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder notesViewHolder, int position) {
        Note note = notes.get(position);
        notesViewHolder.textViewTitle.setText(note.getTitle());
        notesViewHolder.textViewDescription.setText(note.getDescription());
        notesViewHolder.textViewDayOfWeek.setText(note.getDayOfWeek());
        notesViewHolder.textViewPriority.setText(String.format("%s", note.getPriority()));
        switch (note.getPriority()){
            case (1):
                notesViewHolder.imageViewNotes.setImageResource(R.drawable.previewdialog);
                break;
            case (2):
                notesViewHolder.imageViewNotes.setImageResource(R.drawable.previewdialogf);
                break;
            case (3):
                notesViewHolder.imageViewNotes.setImageResource(R.drawable.previewdialogt);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    class NotesViewHolder extends RecyclerView.ViewHolder{ //держатель видимых частей
        private TextView textViewTitle;
        private TextView textViewDayOfWeek;
        private TextView textViewDescription;
        private TextView textViewPriority;
        private ImageView imageViewNotes;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewPriority = itemView.findViewById(R.id.textViewPriority);
            imageViewNotes = itemView.findViewById(R.id.imageViewNotes);
        }
    }
}
