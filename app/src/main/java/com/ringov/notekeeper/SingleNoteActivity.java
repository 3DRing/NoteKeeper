package com.ringov.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SingleNoteActivity extends AppCompatActivity {

    private boolean editMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_note_activity);

        editMode = false;

        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        final TextInputLayout etTitle = (TextInputLayout) findViewById(R.id.et_title);
        etTitle.setVisibility(View.GONE);
        final TextView tvTitle = (TextView) findViewById(R.id.tv_title_single_note);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TextView content = (TextView) findViewById(R.id.tv_text);

        Intent intent = getIntent();
        NoteEntry entry = (NoteEntry) intent.getSerializableExtra("entry"); // todo remove hardcoded text
        if(entry != null) {
            String title = entry.getTitle();
            String text = entry.getText();
            tvTitle.setText(title);
            content.setText(text);
        }
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editMode) {
                    tvTitle.setVisibility(View.GONE);
                    etTitle.setVisibility(View.VISIBLE);
                    etTitle.getEditText().setText(tvTitle.getText().toString());
                    editMode = true;
                }else{
                    etTitle.setVisibility(View.GONE);
                    tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.setText(etTitle.getEditText().getText().toString());
                    editMode = false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_change_storage) {
            ChangeStorageDialog changeStorageDialog = new ChangeStorageDialog();
            changeStorageDialog.show(getSupportFragmentManager(),"change_storage_dialog");  //TODO remove hardcoded text
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
