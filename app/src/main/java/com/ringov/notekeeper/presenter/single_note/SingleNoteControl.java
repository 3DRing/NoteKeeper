package com.ringov.notekeeper.presenter.single_note;

import com.ringov.notekeeper.view.interfaces.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.presenter.base.BaseControl;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface SingleNoteControl extends BaseControl {
    void loadNote(int id, ContextProvider contextProvider);
    void commitNote(NoteEntry entry, boolean creating, ContextProvider contextProvider);
}
