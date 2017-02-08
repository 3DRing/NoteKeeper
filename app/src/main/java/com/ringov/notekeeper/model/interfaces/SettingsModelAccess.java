package com.ringov.notekeeper.model.interfaces;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.view.interfaces.ContextProvider;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface SettingsModelAccess extends BaseModelAccess {
    StorageMap.STORAGE_TYPE getCurrentStorageType(ContextProvider contextProvider);
    void changeCurrentStorageType(StorageMap.STORAGE_TYPE type, ContextProvider contextProvider);

    boolean isSmsFetchingEnabled(ContextProvider contextProvide);

    void changeSmsFetchingEnabled(boolean enabled, ContextProvider contextProvide);
}
