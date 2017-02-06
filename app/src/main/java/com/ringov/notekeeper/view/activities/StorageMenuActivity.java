package com.ringov.notekeeper.view.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.ringov.notekeeper.R;
import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.presenter.PresenterManager;
import com.ringov.notekeeper.presenter.settings.SettingsControl;
import com.ringov.notekeeper.view.fragments.ChangeStorageDialog;
import com.ringov.notekeeper.view.interfaces.SettingsView;

/**
 * Created by Сергей on 04.02.2017.
 */

public abstract class StorageMenuActivity extends BaseActivity implements SettingsView {

    protected SettingsControl settingsControl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsControl = PresenterManager.getSettingsControl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handleClick clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_change_storage) {
            ChangeStorageDialog changeStorageDialog = new ChangeStorageDialog();
            Bundle b = new Bundle();
            b.putSerializable("callback", new ChangeStorageDialog.StorageTypeResultCallback(){ //TODO remove hardcoded text

                @Override
                public void changeStorageType(StorageMap.STORAGE_TYPE type) {
                    settingsControl.changeStorageType(type,
                            StorageMenuActivity.this); //todo remove hardcoded text
                }
            });
            changeStorageDialog.setArguments(b);
            changeStorageDialog.show(getSupportFragmentManager(),"change_storage_dialog");  //TODO remove hardcoded text
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
