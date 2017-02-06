package com.ringov.notekeeper.presenter.single_note;

import com.ringov.notekeeper.presenter.NoteEntry;

/**
 * Created by Сергей on 05.02.2017.
 */

public interface SingleNoteModelControl {
    void createdSuccessfully(boolean success);
    void editedSuccessfully(boolean success);
    void showNote(NoteEntry note);
}
