package com.ringov.notekeeper.presenter.note_list;

import com.ringov.notekeeper.model.ModelManager;
import com.ringov.notekeeper.model.interfaces.NoteListModelAccess;
import com.ringov.notekeeper.presenter.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.presenter.base.BasePresenter;
import com.ringov.notekeeper.view.interfaces.NoteListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */
public class NoteListPresenter extends BasePresenter<NoteListView, NoteListModelAccess>
        implements NoteListControl, NoteListModelControl{
    public NoteListPresenter(NoteListView view) {
        super(view);
        model = ModelManager.getNoteListModel(this);
    }

    @Override
    public void loadNoteList(ContextProvider contextProvider) {
        view.showLoading("");
        model.loadNoteList(contextProvider);
    }

    @Override
    public void sendNoteList(List<NoteEntry> notes) {
        view.stopLoading();
        view.showNoteList(notes);
    }
}
