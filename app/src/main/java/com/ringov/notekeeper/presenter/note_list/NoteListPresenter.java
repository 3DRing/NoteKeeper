package com.ringov.notekeeper.presenter.note_list;

import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.presenter.base.BasePresenter;
import com.ringov.notekeeper.view.interfaces.NoteListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */
public class NoteListPresenter extends BasePresenter<NoteListView> implements NoteListControl{
    public NoteListPresenter(NoteListView view) {
        super(view);
    }

    @Override
    public void loadNoteList() {
        List<NoteEntry> notes = new ArrayList<>();
        notes.add(new NoteEntry(0,"Первая заметка",new Date()));
        view.showNoteList(notes);
    }
}
