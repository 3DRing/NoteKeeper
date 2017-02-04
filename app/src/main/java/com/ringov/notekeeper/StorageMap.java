package com.ringov.notekeeper;

import android.support.annotation.ArrayRes;

/**
 * Created by Сергей on 04.02.2017.
 */

public class StorageMap {
    public static int getStorageId(STORAGE_TYPE type) {
        int id = 0;
        switch (type){
            case SHARED_PREFERENCES:
                id = 0;
                break;
            case SQLITE_DATABASE:
                id = 1;
                break;
            case SDCARD_FILE:
                id = 2;
                break;
            default:
                id = 0;
                break;
        }
        return id;
    }

    public static String getTypeName(STORAGE_TYPE checkType) {
        return checkType.toString(); // todo set actual names
    }

    public enum STORAGE_TYPE {SHARED_PREFERENCES, SQLITE_DATABASE, SDCARD_FILE}

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
