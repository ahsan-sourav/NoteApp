package com.example.noteapp.Adapter;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.Interface.NotesClickListener;
import com.example.noteapp.Model.Notes;
import com.example.noteapp.R;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    Context context;
    List<Notes> notesList;
    NotesClickListener listener;

    public NoteListAdapter(Context context, List<Notes> notesList, NotesClickListener listener) {
        this.context = context;
        this.notesList = notesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.note_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.titleTxt.setText(notesList.get(position).getTitle());
        holder.notesTxt.setText(notesList.get(position).getNotes());
        holder.dateTxt.setText(notesList.get(position).getDate());
        holder.dateTxt.setSelected(true);

        if(notesList.get(position).getPinned()){
            holder.imageView.setImageResource(R.drawable.ic_pin);
        }else{
            holder.imageView.setImageResource(0);
        }
        holder.cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.white));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(notesList.get(holder.getAdapterPosition()));
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongPress(notesList.get(holder.getAdapterPosition()),holder.cardView);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void filterList(List<Notes> filterList){
        notesList = filterList;
        notifyDataSetChanged();
    }

}

class NotesViewHolder extends RecyclerView.ViewHolder{

    CardView cardView;
    TextView notesTxt,titleTxt,dateTxt;
    ImageView imageView;

    public NotesViewHolder(@NonNull View itemView){
        super(itemView);
        cardView = itemView.findViewById(R.id.note_container);
        notesTxt = itemView.findViewById(R.id.notesTxt);
        titleTxt = itemView.findViewById(R.id.titleTxt);
        dateTxt  = itemView.findViewById(R.id.dateTxt);
        imageView = itemView.findViewById(R.id.pinned);
    }
}