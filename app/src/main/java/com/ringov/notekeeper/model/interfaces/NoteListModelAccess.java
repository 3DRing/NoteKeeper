package com.ringov.notekeeper.model.interfaces;

import com.ringov.notekeeper.presenter.android_relations_providers.ContextProvider;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface NoteListModelAccess extends BaseModelAccess {
    void loadNoteList(ContextProvider contextProvider);
    void deleteNote(int id, ContextProvider contextProvider);
}
