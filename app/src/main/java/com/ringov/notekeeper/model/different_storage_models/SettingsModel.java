package com.ringov.notekeeper.model.different_storage_models;

import android.content.Context;
import android.content.SharedPreferences;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.view.interfaces.ContextProvider;

/**
 * Created by Сергей on 04.02.2017.
 */

public class SettingsModel {

    private static final String SHARED_PREFERENCES_TAG = "notekeeper_settings";

    public static void setNextNoteId(ContextProvider contextProvider, int id){
        SharedPreferences sp = contextProvider.extractContext().getSharedPreferences(SHARED_PREFERENCES_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("notes_number", id);
        editor.commit();
    }

    public static int getNextNoteId(ContextProvider contextProvider){
        SharedPreferences sp = contextProvider.extractContext().getSharedPreferences(SHARED_PREFERENCES_TAG, Context.MODE_PRIVATE);
        int id = sp.getInt("notes_number", 0);
        return id;
    }

    public static void updateStorageType(StorageMap.STORAGE_TYPE type, ContextProvider contextProvider){
        SharedPreferences sp = contextProvider.extractContext().getSharedPreferences(SHARED_PREFERENCES_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("storage_type", StorageMap.getStorageId(type)); // todo remove hardcoded text
        editor.commit();
    }

    public static StorageMap.STORAGE_TYPE getStorageType(ContextProvider contextProvider){
        SharedPreferences sp = contextProvider.extractContext().getSharedPreferences("notekeeper_settings", Context.MODE_PRIVATE); //todo remove hardcoded text

        // SQLite storage by default
        int id = sp.getInt("storage_type", StorageMap.getStorageId(StorageMap.STORAGE_TYPE.SQLITE_DATABASE)); // todo remove hardcoded text
        return StorageMap.getStorageType(id);
    }
}
