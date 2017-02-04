package com.ringov.notekeeper.model;

import com.ringov.notekeeper.model.interfaces.SettingsModelAccess;
import com.ringov.notekeeper.presenter.settings.SettingsModelControl;

/**
 * Created by Сергей on 04.02.2017.
 */

public class ModelManager {

    private static BaseModel model;

    private static BaseModel getInstance(){
        if(model != null){
            return model;
        }else{
            return new BaseModel();
        }
    }

    public static SettingsModelAccess getSettingsModel(SettingsModelControl control){
        BaseModel model = getInstance();
        model.setSettingsModelControl(control);
        return model;
    }
}
