package com.example.noteapp.Interface;

import androidx.cardview.widget.CardView;

import com.example.noteapp.Model.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);

    void onLongPress(Notes notes, CardView cardView);
}
