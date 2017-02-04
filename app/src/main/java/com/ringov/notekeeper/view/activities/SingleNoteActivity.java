package com.ringov.notekeeper.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ringov.notekeeper.presenter.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.R;
import com.ringov.notekeeper.presenter.PresenterManager;
import com.ringov.notekeeper.presenter.single_note.SingleNoteControl;
import com.ringov.notekeeper.view.interfaces.SingleNoteView;

import java.util.Date;

public class SingleNoteActivity extends StorageMenuActivity implements SingleNoteView, ContextProvider{

    private NoteEntry entry;
    private SingleNoteEntry localEntry;
    private boolean editMode;

    private TextView tvTitle;
    private EditText etTitle;
    private TextView tvText;
    private EditText etText;
    private TextView tvDate;

    private Toolbar toolbar;
    private FloatingActionButton fab;

    private SingleNoteControl singleNoteControl;
    private boolean creating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_note_activity);

        singleNoteControl = PresenterManager.getSingleNoteControl(this);

        bindViews();
        initializeListeners();

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("edit_mode", false); //todo remove hardcoded text
        entry = (NoteEntry) intent.getSerializableExtra("entry"); // todo remove hardcoded text

        if(!editMode) {
            initializeNoteData(entry);
        }else{
            creating = true;
            localEntry = SingleNoteEntry.emptyNoteEntry();
        }
        changeMode(editMode);
    }

    private void initializeNoteData(NoteEntry note) {
        if(note == null){
            //todo unexpected behaviour, handle somehow
            return;
        }
        this.localEntry = new SingleNoteEntry(note);
        showNote(localEntry);
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
                if(editMode){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    updateLocalEntry();
                    showNote(localEntry);
                    singleNoteControl.commitNote(localEntry,creating, SingleNoteActivity.this);
                }else{
                    editMode = !editMode;
                    changeMode(editMode);
                    beginChangeLocalEntry();
                }
            }
        });
    }

    private void beginChangeLocalEntry(){
        this.etTitle.setText(localEntry.getTitle());
        this.etText.setText(localEntry.getText());
    }

    private void updateLocalEntry(){
        localEntry.setTitle(this.etTitle.getText().toString());
        localEntry.setDate(new Date());
        localEntry.setText(this.etText.getText().toString());
    }

    private void changeMode(boolean editMode) {
        if (editMode) {
            // Toolbar
            tvTitle.setVisibility(View.GONE);
            tvDate.setVisibility(View.GONE);
            etTitle.setVisibility(View.VISIBLE);
            etTitle.requestFocus();

            // Content field
            tvText.setVisibility(View.GONE);
            etText.setVisibility(View.VISIBLE);
        } else {
            // Toolbar
            tvTitle.setVisibility(View.VISIBLE);
            tvDate.setVisibility(View.VISIBLE);
            etTitle.setVisibility(View.GONE);

            // Content field
            tvText.setVisibility(View.VISIBLE);
            etText.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNote(NoteEntry note) {
        tvTitle.setText(note.getTitle());
        tvDate.setText(note.getFormattedDate());
        tvText.setText(note.getText());
    }

    @Override
    public void successCommit() {
        editMode = !editMode;
        changeMode(editMode);

        if(creating){
            creating = false;
            showMessage("Successfully created");
        }else{
            showMessage("Successfully edited");
        }
        setResult(RESULT_OK);
    }

    @Override
    public void failedCommit() {
        if(creating){
            showMessage("Failed creating");
        }else{
            showMessage("Failed editing");
        }
        setResult(RESULT_CANCELED);
    }

    @Override
    public Context extractContext() {
        return this;
    }

    private static class SingleNoteEntry extends NoteEntry{

        public SingleNoteEntry(int id, String title, Date date) {
            super(id, title, date);
        }

        public SingleNoteEntry(NoteEntry note) {
            super(note.getId(),note.getTitle(),note.getDate());
            this.setText(note.getText());
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public static SingleNoteActivity.SingleNoteEntry emptyNoteEntry() {
            return new SingleNoteEntry(-1, "", new Date()); // -1 means this entry should be created
        }
    }
}
