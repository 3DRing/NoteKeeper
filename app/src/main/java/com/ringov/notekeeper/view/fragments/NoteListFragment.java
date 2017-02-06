package com.ringov.notekeeper.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ringov.notekeeper.presenter.android_relations_providers.ContextProvider;
import com.ringov.notekeeper.presenter.PresenterManager;
import com.ringov.notekeeper.presenter.note_list.NoteListControl;
import com.ringov.notekeeper.view.interfaces.EntryClick;
import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.view.NoteListAdapter;
import com.ringov.notekeeper.R;
import com.ringov.notekeeper.view.activities.SingleNoteActivity;
import com.ringov.notekeeper.view.interfaces.BaseView;
import com.ringov.notekeeper.view.interfaces.NoteListView;

import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */

public class NoteListFragment extends BaseFragment implements NoteListView, ContextProvider{

    public static String TAG = "NoteListFragment";

    private SwipeRefreshLayout updateLayout;
    private RecyclerView rv;
    private NoteListAdapter adapter;
    private List<NoteEntry> tmpList;

    private NoteListControl noteListControl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_list_fragment,container, false);

        noteListControl = PresenterManager.getNoteListControl(this);

        // order matters
        bindViews(view);
        initializeListeners();

        initializeData();

        return view;
    }

    private void initializeListeners() {
        updateLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                update();
                updateLayout.setRefreshing(false);
            }
        });
    }

    private void initializeData() {
        noteListControl.loadNoteList(this);
    }

    private void bindViews(View view){
        initializeRecyclerView(view);
        updateLayout = (SwipeRefreshLayout) view.findViewById(R.id.update_layout);
    }

    private void initializeRecyclerView(View view){
        rv = (RecyclerView) view.findViewById(R.id.rv_note_list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new NoteListAdapter(new EntryClick() {
            @Override
            public void handleClick(NoteEntry entry) {
                Intent intent = new Intent(getContext(), SingleNoteActivity.class);
                intent.putExtra("id", entry.getId());
                startActivityForResult(intent,1);
            }

            @Override
            public void handleLongClick(final NoteEntry entry) {
                DeleteRecordDialog deleteRecordDialog = new DeleteRecordDialog();
                Bundle b = new Bundle();
                b.putSerializable("callback", new DeleteRecordDialog.DeleteCallback(){ //TODO remove hardcoded text
                    @Override
                    public void deleteNote() {
                        noteListControl.deleteNote(entry.getId(), NoteListFragment.this);
                    }
                });
                deleteRecordDialog.setArguments(b);
                deleteRecordDialog.show(getActivity().getSupportFragmentManager(),"delete_note");  //TODO remove hardcoded text
            }
        });

        rv.setAdapter(adapter);
        if(tmpList != null){
            adapter.updateAll(tmpList);
            tmpList = null;
        }
    }

    @Override
    public void showNoteList(List<NoteEntry> noteList) {
        if(adapter != null) {
            adapter.updateAll(noteList);
        }else{
            tmpList = noteList;
        }
    }

    @Override
    public void update() {
        noteListControl.loadNoteList(this);
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

    @Override
    public Context extractContext() {
        return getContext();
    }
}
