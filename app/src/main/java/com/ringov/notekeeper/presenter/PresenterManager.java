package com.ringov.notekeeper.presenter;

import com.ringov.notekeeper.presenter.note_list.NoteListControl;
import com.ringov.notekeeper.presenter.note_list.NoteListPresenter;
import com.ringov.notekeeper.presenter.settings.SettingsControl;
import com.ringov.notekeeper.presenter.settings.SettingsPresenter;
import com.ringov.notekeeper.presenter.single_note.SingleNoteControl;
import com.ringov.notekeeper.presenter.single_note.SingleNotePresenter;
import com.ringov.notekeeper.view.interfaces.NoteListView;
import com.ringov.notekeeper.view.interfaces.SettingsView;
import com.ringov.notekeeper.view.interfaces.SingleNoteView;

/**
 * Created by Сергей on 04.02.2017.
 */

public class PresenterManager {

    public static NoteListControl getNoteListControl(NoteListView view){
        return new NoteListPresenter(view);
    }

    public static SingleNoteControl getSingleNoteControl(SingleNoteView view){
        return new SingleNotePresenter(view);
    }

    public static SettingsControl getSettingsControl(SettingsView view){
        return new SettingsPresenter(view);
    }
}
