package com.ringov.notekeeper.presenter.note_list;

import com.ringov.notekeeper.presenter.ContextProvider;
import com.ringov.notekeeper.presenter.base.BaseControl;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface NoteListControl extends BaseControl {
    void loadNoteList(ContextProvider contextProvider);
}
