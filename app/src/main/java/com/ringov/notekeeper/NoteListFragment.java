package com.ringov.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */

public class NoteListFragment extends Fragment {

    public static String TAG = "NoteListFragment";

    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_list_fragment,container, false);

        initializeRecyclerView(view);

        return view;
    }

    private void initializeRecyclerView(View view){
        rv = (RecyclerView) view.findViewById(R.id.rv_note_list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        NoteListAdapter adapter = new NoteListAdapter(new EntryClick() {
            @Override
            public void handle(NoteEntry entry) {
                Intent intent = new Intent(getContext(), SingleNoteActivity.class);
                intent.putExtra("entry", entry); // todo remove hardcoded text
                startActivity(intent);
            }
        });

        rv.setAdapter(adapter);

        // todo change testset to the real one
        List<NoteEntry> notes = new ArrayList<>();
        notes.add(new NoteEntry(0,"Первая заметка",new Date()));

        adapter.addAll(notes);
    }
}
