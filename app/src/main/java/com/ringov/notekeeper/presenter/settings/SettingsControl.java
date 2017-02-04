package com.ringov.notekeeper.presenter.settings;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.presenter.base.BaseControl;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface SettingsControl extends BaseControl {
    void changeStorageType(StorageMap.STORAGE_TYPE type);
}
