package com.ringov.notekeeper;

import android.support.annotation.ArrayRes;

/**
 * Created by Сергей on 04.02.2017.
 */

public class StorageMap {
    enum STORAGE_TYPE {SHARED_PREFERENCES, SQLITE_DATABASE, SDCARD_FILE}

    @ArrayRes
    public static int getStorageTypeArrayRes(){
        return R.array.storage_list;
    }

    public static STORAGE_TYPE getStorageType(int which){
        // mapping from R.array.storage_list to enums

        STORAGE_TYPE type;
        switch (which){
            case 0:
                type = STORAGE_TYPE.SHARED_PREFERENCES;
                break;
            case 1:
                type = STORAGE_TYPE.SQLITE_DATABASE;
                break;
            case 2:
                type = STORAGE_TYPE.SDCARD_FILE;
                break;
            default:
                // Save to sqlite db by default
                type = STORAGE_TYPE.SQLITE_DATABASE;
        }
        return type;
    }
}
