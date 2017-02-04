package com.ringov.notekeeper.model;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.model.different_storage_models.SettingsModel;
import com.ringov.notekeeper.model.interfaces.SettingsModelAccess;
import com.ringov.notekeeper.presenter.settings.SettingsModelControl;
import com.ringov.notekeeper.view.interfaces.SharedPreferencesProvider;

/**
 * Created by Сергей on 04.02.2017.
 */

public class BaseModel implements SettingsModelAccess {

    private SettingsModelControl settingsModelControl;

    public void setSettingsModelControl(SettingsModelControl control) {
        this.settingsModelControl = control;
    }

    @Override
    public StorageMap.STORAGE_TYPE getCurrentStorageType(SharedPreferencesProvider storageSettingsProvider) {
        return SettingsModel.getStorageType(storageSettingsProvider);
    }

    @Override
    public void changeCurrentStorageType(StorageMap.STORAGE_TYPE type, SharedPreferencesProvider storageSettingsProvider) {
        SettingsModel.updateStorageType(type,storageSettingsProvider);
    }
}
