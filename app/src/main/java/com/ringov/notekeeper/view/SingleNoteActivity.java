package com.ringov.notekeeper.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ringov.notekeeper.EntryClick;
import com.ringov.notekeeper.NoteEntry;
import com.ringov.notekeeper.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SingleNoteActivity extends StorageMenuActivity {

    private NoteEntry entry;
    private boolean editMode;

    private TextView tvTitle;
    private EditText etTitle;
    private TextView tvText;
    private EditText etText;
    private TextView tvDate;

    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_note_activity);

        bindViews();
        initializeListeners();

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("edit_mode", false); //todo remove hardcoded text
        entry = (NoteEntry) intent.getSerializableExtra("entry"); // todo remove hardcoded text

        runInterface(editMode);

        if(!editMode) {
            fillNoteData(entry);
        }

    }

    private void fillNoteData(NoteEntry note) {
        if(note == null){
            //todo unexpected behaviour, handle somehow
            return;
        }
        this.tvTitle.setText(note.getTitle());
        this.tvText.setText(note.getText());
    }

    @Override
    protected void bindViews(){
        tvTitle = (TextView) findViewById(R.id.tv_title_single_note);
        etTitle = (EditText) findViewById(R.id.et_title);
        tvText = (TextView) findViewById(R.id.tv_text);
        etText = (EditText) findViewById(R.id.et_text);
        tvDate = (TextView) findViewById(R.id.tv_date_single_note);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void initializeListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMode = !editMode;
                runInterface(editMode);
                if(!editMode){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
    }

    private void runInterface(boolean editMode){
        if(editMode) {
            // Toolbar
            tvTitle.setVisibility(View.GONE);
            tvDate.setVisibility(View.GONE);
            etTitle.setVisibility(View.VISIBLE);
            etTitle.requestFocus();
            etTitle.setText(tvTitle.getText().toString());

            // Content field
            tvText.setVisibility(View.GONE);
            etText.setVisibility(View.VISIBLE);
            etText.setText(tvText.getText().toString());
        }else{
            // Toolbar
            tvTitle.setVisibility(View.VISIBLE);
            tvDate.setVisibility(View.VISIBLE);
            etTitle.setVisibility(View.GONE);

            tvTitle.setText(etTitle.getText().toString());
            tvDate.setText(NoteEntry.getSimpleDateFormat().format(new Date())); //todo change to more unified way

            // Content field
            tvText.setVisibility(View.VISIBLE);
            etText.setVisibility(View.GONE);
            tvText.setText(etText.getText().toString());
        }
    }
}
