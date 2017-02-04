package com.ringov.notekeeper.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ringov.notekeeper.view.interfaces.EntryClick;
import com.ringov.notekeeper.NoteEntry;
import com.ringov.notekeeper.view.NoteListAdapter;
import com.ringov.notekeeper.R;
import com.ringov.notekeeper.view.activities.SingleNoteActivity;
import com.ringov.notekeeper.view.interfaces.BaseView;
import com.ringov.notekeeper.view.interfaces.NoteListView;

import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */

public class NoteListFragment extends BaseFragment implements NoteListView{

    public static String TAG = "NoteListFragment";

    private RecyclerView rv;
    private NoteListAdapter adapter;
    private List<NoteEntry> tmpList;

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

        adapter = new NoteListAdapter(new EntryClick() {
            @Override
            public void handle(NoteEntry entry) {
                Intent intent = new Intent(getContext(), SingleNoteActivity.class);
                intent.putExtra("entry", entry); // todo remove hardcoded text
                startActivity(intent);
            }
        });

        rv.setAdapter(adapter);
        if(tmpList != null){
            adapter.addAll(tmpList);
            tmpList = null;
        }
    }

    @Override
    public void showNoteList(List<NoteEntry> noteList) {
        if(adapter != null) {
            adapter.addAll(noteList);
        }else{
            tmpList = noteList;
        }
    }

    @Override
    public void showMessage(String message) {
        baseViewProvider.showMessage(message);
    }

    @Override
    public void showLoading(String message) {
        baseViewProvider.showLoading(message);
    }

    @Override
    public void stopLoading() {
        baseViewProvider.stopLoading();
    }

    @Override
    public void setBaseView(BaseView baseViewProvider) {
        this.baseViewProvider = baseViewProvider;
    }
}
