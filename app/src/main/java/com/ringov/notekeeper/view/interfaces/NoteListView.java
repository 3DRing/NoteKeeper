package com.ringov.notekeeper.view.interfaces;

import com.ringov.notekeeper.presenter.NoteEntry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface NoteListView extends BaseView {
    void showNoteList(List<NoteEntry> noteList);
    void update();
}
