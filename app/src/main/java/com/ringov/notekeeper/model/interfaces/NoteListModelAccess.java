package com.ringov.notekeeper.model.interfaces;

import com.ringov.notekeeper.view.interfaces.ContextProvider;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface NoteListModelAccess extends BaseModelAccess {
    void loadNoteList(ContextProvider contextProvider);
    void deleteNote(int id, ContextProvider contextProvider);
}
