package com.ringov.notekeeper.presenter.settings;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.model.ModelManager;
import com.ringov.notekeeper.model.interfaces.SettingsModelAccess;
import com.ringov.notekeeper.presenter.base.BasePresenter;
import com.ringov.notekeeper.view.interfaces.SettingsView;
import com.ringov.notekeeper.presenter.SharedPreferencesProvider;

/**
 * Created by Сергей on 04.02.2017.
 */
public class SettingsPresenter extends BasePresenter<SettingsView, SettingsModelAccess>
        implements SettingsControl, SettingsModelControl{
    public SettingsPresenter(SettingsView view) {
        super(view);
        this.model = ModelManager.getSettingsModel(this);
    }

    @Override
    public void changeStorageType(StorageMap.STORAGE_TYPE type, SharedPreferencesProvider storageSettingsProvider) {
        model.changeCurrentStorageType(type, storageSettingsProvider);

        //checking of a successful change
        StorageMap.STORAGE_TYPE checkType = model.getCurrentStorageType(storageSettingsProvider);
        view.showMessage(StorageMap.getTypeName(checkType));
    }

    @Override
    public StorageMap.STORAGE_TYPE getStorageType(SharedPreferencesProvider storageSettingsProvider) {
        return model.getCurrentStorageType(storageSettingsProvider);
    }
}
