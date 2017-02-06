package com.ringov.notekeeper.model.interfaces;

import com.ringov.notekeeper.view.interfaces.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface SingleNoteModelAccess extends BaseModelAccess {
    void loadNote(int id, ContextProvider contextProvider);
    void commitNote(NoteEntry note, boolean creating, ContextProvider contextProvider);
}
