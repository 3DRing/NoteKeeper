package com.ringov.notekeeper.view.interfaces;

import com.ringov.notekeeper.presenter.NoteEntry;

/**
 * Created by Сергей on 04.02.2017.
 */
public interface EntryClick {
    void handleClick(NoteEntry entry);
    void handleLongClick(NoteEntry entry);
}
