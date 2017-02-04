package com.ringov.notekeeper.presenter.settings;

import android.content.SharedPreferences;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.presenter.base.BaseControl;
import com.ringov.notekeeper.view.interfaces.SharedPreferencesProvider;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface SettingsControl extends BaseControl {
    void changeStorageType(StorageMap.STORAGE_TYPE type, SharedPreferencesProvider storageSettingsProvider);

    StorageMap.STORAGE_TYPE getStorageType(SharedPreferencesProvider storageSettingsProvider);
}
