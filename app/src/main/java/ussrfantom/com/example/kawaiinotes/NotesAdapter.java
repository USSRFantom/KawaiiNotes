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
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
        private List<Note> notes;
        private OnNoteClickListener onNoteClickListener;

    public NotesAdapter(ArrayList<Note> note) {
        this.notes = note;
    }

    interface OnNoteClickListener {
        void onNoteClick(int position);
        void onLongClock(int position);
    }
    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
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
        notesViewHolder.textViewDayOfWeek.setText(Note.getDayAsString(note.getDayOfWeek() + 1));
        int colorId;
        switch (note.getPriority()){
            case (1):
                notesViewHolder.imageViewNotes.setImageResource(R.drawable.previewdialog);
                colorId = notesViewHolder.itemView.getResources().getColor(android.R.color.holo_red_light);
                notesViewHolder.textViewTitle.setBackgroundColor(colorId);
                break;
            case (2):
                notesViewHolder.imageViewNotes.setImageResource(R.drawable.previewdialogf);
                colorId = notesViewHolder.itemView.getResources().getColor(android.R.color.holo_orange_light);
                notesViewHolder.textViewTitle.setBackgroundColor(colorId);
                break;
            case (3):
                notesViewHolder.imageViewNotes.setImageResource(R.drawable.previewdialogt);
                colorId = notesViewHolder.itemView.getResources().getColor(android.R.color.holo_green_light);
                notesViewHolder.textViewTitle.setBackgroundColor(colorId);
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
        private ImageView imageViewNotes;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewNotes = itemView.findViewById(R.id.imageViewNotes);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteClickListener != null){
                        onNoteClickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onNoteClickListener != null){
                        onNoteClickListener.onLongClock(getAdapterPosition());
                    }
                    return true;
                }
            });

        }
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public List<Note> getNotes() {
        return notes;
    }
}
