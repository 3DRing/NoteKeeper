package com.ringov.notekeeper.presenter.note_list;

import com.ringov.notekeeper.presenter.NoteEntry;

import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */
public interface NoteListModelControl {
    void sendNoteList(List<NoteEntry> notes);
}
