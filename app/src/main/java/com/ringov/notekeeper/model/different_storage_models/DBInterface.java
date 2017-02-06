package com.ringov.notekeeper.model.different_storage_models;

import com.ringov.notekeeper.presenter.android_relations_providers.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;

import java.util.List;

/**
 * Created by Сергей on 05.02.2017.
 */

public interface DBInterface {
    List<NoteEntry> getNoteList(ContextProvider contextProvider);
    boolean addNote(NoteEntry note, ContextProvider contextProvider);
    boolean editNote(NoteEntry note, ContextProvider contextProvider);
    boolean deleteNote(int id, ContextProvider contextProvider);
    NoteEntry loadNote(int id, ContextProvider contextProvider);
}
