package com.ringov.notekeeper.model;

import com.ringov.notekeeper.model.interfaces.NoteListModelAccess;
import com.ringov.notekeeper.model.interfaces.SettingsModelAccess;
import com.ringov.notekeeper.model.interfaces.SingleNoteModelAccess;
import com.ringov.notekeeper.presenter.note_list.NoteListModelControl;
import com.ringov.notekeeper.presenter.settings.SettingsModelControl;
import com.ringov.notekeeper.presenter.single_note.SingleNoteModelControl;
import com.ringov.notekeeper.presenter.single_note.SingleNotePresenter;

/**
 * Created by Сергей on 04.02.2017.
 */

public class ModelManager {

    private static BaseModel model;
    private static NoteListModelAccess noteListModel;

    private static BaseModel getInstance(){
        if(model != null){
            return model;
        }else{
            return new BaseModel();
        }
    }

    public static SettingsModelAccess getSettingsModel(SettingsModelControl control){
        BaseModel model = getInstance();
        model.setSettingsModelControl(control);
        return model;
    }

    public static NoteListModelAccess getNoteListModel(NoteListModelControl control) {
        BaseModel model = getInstance();
        model.setNoteListModelControl(control);
        return model;
    }

    public static SingleNoteModelAccess getSingleNoteModel(SingleNoteModelControl control) {
        BaseModel model = getInstance();
        model.setSingleNoteModelControl(control);
        return model;
    }
}
