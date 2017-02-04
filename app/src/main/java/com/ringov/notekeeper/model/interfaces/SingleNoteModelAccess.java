package com.ringov.notekeeper.model.interfaces;

import com.ringov.notekeeper.presenter.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface SingleNoteModelAccess extends BaseModelAccess {
    void addNote(NoteEntry note, ContextProvider contextProvider);
    void editNote(int id, ContextProvider contextProvider);
}
