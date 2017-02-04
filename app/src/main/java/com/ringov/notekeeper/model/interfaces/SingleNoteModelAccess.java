package com.ringov.notekeeper.model.interfaces;

import com.ringov.notekeeper.presenter.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface SingleNoteModelAccess extends BaseModelAccess {
    void commitNote(NoteEntry note, boolean creating, ContextProvider contextProvider);
}