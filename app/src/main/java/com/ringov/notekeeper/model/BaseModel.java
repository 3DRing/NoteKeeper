package com.ringov.notekeeper.model;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.model.different_storage_models.DBInterface;
import com.ringov.notekeeper.model.different_storage_models.ExternalCardDB;
import com.ringov.notekeeper.model.different_storage_models.SQLiteDB;
import com.ringov.notekeeper.model.different_storage_models.SettingsModel;
import com.ringov.notekeeper.model.interfaces.NoteListModelAccess;
import com.ringov.notekeeper.model.interfaces.SettingsModelAccess;
import com.ringov.notekeeper.model.interfaces.SingleNoteModelAccess;
import com.ringov.notekeeper.view.interfaces.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.presenter.note_list.NoteListModelControl;
import com.ringov.notekeeper.presenter.settings.SettingsModelControl;
import com.ringov.notekeeper.presenter.single_note.SingleNoteModelControl;

import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */

public class BaseModel
        implements
        SettingsModelAccess,
        NoteListModelAccess,
        SingleNoteModelAccess {

    // presenter controllers
    private SettingsModelControl settingsModelControl;
    private NoteListModelControl noteListModelControl;
    private SingleNoteModelControl singleNoteModelControl;

    private DBInterface dbInterface;

    public BaseModel(ContextProvider contextProvider) {
        StorageMap.STORAGE_TYPE type = SettingsModel.getStorageType(contextProvider);
        dbInterface = chooseDBAccordingToStorageType(type);
    }

    private DBInterface chooseDBAccordingToStorageType(StorageMap.STORAGE_TYPE type){
        DBInterface db = null;
        switch (type) {
            case SQLITE_DATABASE:
                db = new SQLiteDB();
                break;
            case SDCARD_FILE:
                db = new ExternalCardDB();
                break;
            case SHARED_PREFERENCES:
                // until implementing
                //break;
            default:
                db = new SQLiteDB();
                break;
        }
        if(db == null){
            db = new SQLiteDB();
        }
        return db;
    }

    public void setSettingsModelControl(SettingsModelControl control) {
        this.settingsModelControl = control;
    }

    @Override
    public StorageMap.STORAGE_TYPE getCurrentStorageType(ContextProvider contextProvider) {
        return SettingsModel.getStorageType(contextProvider);
    }

    @Override
    public void changeCurrentStorageType(StorageMap.STORAGE_TYPE type, ContextProvider contextProvider) {
        StorageMap.STORAGE_TYPE crtType = SettingsModel.getStorageType(contextProvider);
        SettingsModel.updateStorageType(type, contextProvider);
        if (crtType != type) {
            dbInterface = chooseDBAccordingToStorageType(type);
        }
    }

    @Override
    public void loadNoteList(ContextProvider contextProvider) {
        List<NoteEntry> notes = dbInterface.getNoteList(contextProvider);
        noteListModelControl.sendNoteList(notes);
    }

    @Override
    public void deleteNote(int id, ContextProvider contextProvider) {
        boolean success = dbInterface.deleteNote(id, contextProvider);
        noteListModelControl.deletedSuccessfully(success);
    }

    public void setNoteListModelControl(NoteListModelControl noteListModelControl) {
        this.noteListModelControl = noteListModelControl;
    }

    @Override
    public void loadNote(int id, ContextProvider contextProvider) {
        NoteEntry note = dbInterface.loadNote(id, contextProvider);
        if (singleNoteModelControl != null) {
            singleNoteModelControl.showNote(note);
        }
    }

    @Override
    public void commitNote(NoteEntry note, boolean creating, ContextProvider contextProvider) {
        boolean success;
        if (creating) {
            int id = SettingsModel.getNextNoteId(contextProvider);// todo rewrite it in more convenient way
            NoteEntry withId = new NoteEntry(id, note.getTitle(), note.getDate());
            withId.setText(note.getText());

            success = dbInterface.addNote(withId, contextProvider);
            if (success) {
                SettingsModel.setNextNoteId(contextProvider, id + 1);
            }
            if (singleNoteModelControl != null) {
                singleNoteModelControl.createdSuccessfully(success);
            }
        } else {
            success = dbInterface.editNote(note, contextProvider);
            if (singleNoteModelControl != null) {
                singleNoteModelControl.editedSuccessfully(success);
            }
        }

    }

    public void setSingleNoteModelControl(SingleNoteModelControl singleNoteModelControl) {
        this.singleNoteModelControl = singleNoteModelControl;
    }
}
