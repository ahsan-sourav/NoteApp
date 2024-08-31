package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noteapp.Model.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakeActivity extends AppCompatActivity {

    EditText titleED,notesED;
    ImageView saveBtn;
    Notes notes;

    boolean isOldNotes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_take);

        saveBtn = findViewById(R.id.saveBtn);
        titleED = findViewById(R.id.titleEdt);
        notesED = findViewById(R.id.noteEdt);

        notes = new Notes();
        try {
          notes = (Notes) getIntent().getSerializableExtra("old_notes");
          titleED.setText(notes.getTitle());
          notesED.setText(notes.getNotes());
          isOldNotes = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isOldNotes) {
                    notes = new Notes();
                }
                String title = titleED.getText().toString();
                String description = notesED.getText().toString();

                if(description.isEmpty()){
                    Toast.makeText(NotesTakeActivity.this,"Please enter the descrption",Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM HH:mm a");
                Date date =new Date();

                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(format.format(date));

                Intent intent = new Intent();
                intent.putExtra("note",notes);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}