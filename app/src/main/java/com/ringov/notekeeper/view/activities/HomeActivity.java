package com.ringov.notekeeper.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ringov.notekeeper.R;
import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.view.fragments.NoteListFragment;
import com.ringov.notekeeper.view.interfaces.NoteListView;

public class HomeActivity extends StorageMenuActivity {

    private FloatingActionButton fab;
    private Toolbar toolbar;

    private NoteListView noteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // order matters
        bindViews();
        initializeListeners();
        //-----------

        // start initial fragment
        NoteListFragment fragment = new NoteListFragment();
        fragment.setBaseView(this);
        noteListView = fragment;
        startFragment(fragment);
    }

    @Override
    protected void bindViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initializeListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SingleNoteActivity.class);
                intent.putExtra("edit_mode", true); // todo remove hardcoded text
                startActivityForResult(intent, 1);
            }
        });
    }

    private void startFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            noteListView.update();
        }
    }
}
