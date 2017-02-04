package com.ringov.notekeeper.presenter.settings;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.presenter.base.BasePresenter;
import com.ringov.notekeeper.presenter.settings.SettingsControl;
import com.ringov.notekeeper.view.interfaces.SettingsView;

/**
 * Created by Сергей on 04.02.2017.
 */
public class SettingsPresenter extends BasePresenter<SettingsView> implements SettingsControl {
    public SettingsPresenter(SettingsView view) {
        super(view);
    }

    @Override
    public void changeStorageType(StorageMap.STORAGE_TYPE type) {
        view.showMessage(type.toString());
    }
}
