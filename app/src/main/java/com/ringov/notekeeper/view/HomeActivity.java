package com.ringov.notekeeper.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ringov.notekeeper.NoteListFragment;
import com.ringov.notekeeper.R;

public class HomeActivity extends StorageMenuActivity {

    private FloatingActionButton fab;
    private FrameLayout fragmentContainer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // order matters
        bindViews();
        initializeListeners();
        //-----------

        // start initial fragment
        startFragment(new NoteListFragment());
    }

    @Override
    protected void bindViews(){
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    protected void initializeListeners(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
