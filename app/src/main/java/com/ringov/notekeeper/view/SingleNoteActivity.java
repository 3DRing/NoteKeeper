package com.ringov.notekeeper.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.ringov.notekeeper.EntryClick;
import com.ringov.notekeeper.NoteEntry;
import com.ringov.notekeeper.R;

public class SingleNoteActivity extends StorageMenuActivity {

    private NoteEntry entry;
    private boolean editMode;

    private TextInputLayout etTitle;
    private TextView tvTitle;
    private TextView tvText;

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
        etTitle = (TextInputLayout) findViewById(R.id.et_title);
        tvTitle = (TextView) findViewById(R.id.tv_title_single_note);
        tvText = (TextView) findViewById(R.id.tv_text);
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
            tvTitle.setVisibility(View.GONE);
            etTitle.setVisibility(View.VISIBLE);
            etTitle.requestFocus();
            etTitle.getEditText().setText(tvTitle.getText().toString());
        }else{
            etTitle.setVisibility(View.GONE);
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(etTitle.getEditText().getText().toString());
        }
    }
}
