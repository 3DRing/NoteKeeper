package com.ringov.notekeeper.view.interfaces;

import com.ringov.notekeeper.presenter.NoteEntry;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface SingleNoteView extends BaseView {
    void showNote(NoteEntry note);
    void successCommit();
    void failedCommit();
}
