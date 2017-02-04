package com.ringov.notekeeper.model.different_storage_models;

import android.content.SharedPreferences;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.view.interfaces.SharedPreferencesProvider;

/**
 * Created by Сергей on 04.02.2017.
 */

public class SettingsModel {

    public static void updateStorageType(StorageMap.STORAGE_TYPE type, SharedPreferencesProvider storageSettingsProvider){
        SharedPreferences sp = storageSettingsProvider.getSharedPreferences("notekeeper_settings"); //todo remove hardcoded text
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("storage_type", StorageMap.getStorageId(type)); // todo remove hardcoded text
        editor.commit();
    }

    public static StorageMap.STORAGE_TYPE getStorageType(SharedPreferencesProvider storageSettingsProvider){
        SharedPreferences sp = storageSettingsProvider.getSharedPreferences("notekeeper_settings"); //todo remove hardcoded text

        // SQLite storage by default
        int id = sp.getInt("storage_type", StorageMap.getStorageId(StorageMap.STORAGE_TYPE.SQLITE_DATABASE)); // todo remove hardcoded text
        return StorageMap.getStorageType(id);
    }
}
