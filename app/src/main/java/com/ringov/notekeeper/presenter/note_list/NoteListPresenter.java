package com.ringov.notekeeper.presenter.note_list;

import com.ringov.notekeeper.presenter.base.BasePresenter;
import com.ringov.notekeeper.view.interfaces.NoteListView;

/**
 * Created by Сергей on 04.02.2017.
 */
public class NoteListPresenter extends BasePresenter<NoteListView> implements NoteListControl{
    public NoteListPresenter(NoteListView view) {
        super(view);
    }

    @Override
    public void loadNoteList() {

    }
}
