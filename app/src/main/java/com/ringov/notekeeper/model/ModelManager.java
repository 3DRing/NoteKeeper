package com.ringov.notekeeper.model;

import android.support.annotation.Nullable;

import com.ringov.notekeeper.model.interfaces.NoteListModelAccess;
import com.ringov.notekeeper.model.interfaces.SettingsModelAccess;
import com.ringov.notekeeper.model.interfaces.SingleNoteModelAccess;
import com.ringov.notekeeper.view.interfaces.ContextProvider;
import com.ringov.notekeeper.presenter.note_list.NoteListModelControl;
import com.ringov.notekeeper.presenter.settings.SettingsModelControl;
import com.ringov.notekeeper.presenter.single_note.SingleNoteModelControl;

/**
 * Created by Сергей on 04.02.2017.
 */

public class ModelManager {

    private static BaseModel model;
    private static NoteListModelAccess noteListModel;

    private static BaseModel getInstance(ContextProvider contextProvider){
        if(model != null){
            return model;
        }else{
            return new BaseModel(contextProvider);
        }
    }

    public static SettingsModelAccess getSettingsModel(SettingsModelControl control,
                                                       ContextProvider contextProvider){
        BaseModel model = getInstance(contextProvider);
        model.setSettingsModelControl(control);
        return model;
    }

    public static NoteListModelAccess getNoteListModel(NoteListModelControl control,
                                                       ContextProvider contextProvider) {
        BaseModel model = getInstance(contextProvider);
        model.setNoteListModelControl(control);
        return model;
    }

    public static SingleNoteModelAccess getSingleNoteModel(@Nullable SingleNoteModelControl control,
                                                           ContextProvider contextProvider) {
        BaseModel model = getInstance(contextProvider);
        model.setSingleNoteModelControl(control);
        return model;
    }
}
