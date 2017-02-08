package com.ringov.notekeeper.presenter.settings;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.model.ModelManager;
import com.ringov.notekeeper.model.interfaces.SettingsModelAccess;
import com.ringov.notekeeper.presenter.base.BasePresenter;
import com.ringov.notekeeper.view.interfaces.SettingsView;
import com.ringov.notekeeper.view.interfaces.ContextProvider;

/**
 * Created by Сергей on 04.02.2017.
 */
public class SettingsPresenter extends BasePresenter<SettingsView, SettingsModelAccess>
        implements SettingsControl, SettingsModelControl{
    public SettingsPresenter(SettingsView view) {
        super(view);
        this.model = ModelManager.getSettingsModel(this, view);
    }

    @Override
    public void changeStorageType(StorageMap.STORAGE_TYPE type, ContextProvider contextProvider) {
        model.changeCurrentStorageType(type, contextProvider);

        view.onStorageTypeChangedUpdate();
        //view.showMessage("Storage type has been changed\n" + type);
    }

    @Override
    public StorageMap.STORAGE_TYPE getStorageType(ContextProvider contextProvider) {
        return model.getCurrentStorageType(contextProvider);
    }
}
