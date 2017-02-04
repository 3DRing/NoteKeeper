package com.ringov.notekeeper.view.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ringov.notekeeper.R;
import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.presenter.PresenterManager;
import com.ringov.notekeeper.presenter.settings.SettingsControl;
import com.ringov.notekeeper.view.fragments.ChangeStorageDialog;
import com.ringov.notekeeper.view.interfaces.SettingsView;
import com.ringov.notekeeper.view.interfaces.SharedPreferencesProvider;

/**
 * Created by Сергей on 04.02.2017.
 */

public abstract class StorageMenuActivity extends AppCompatActivity implements SettingsView, SharedPreferencesProvider {

    protected abstract void bindViews();
    protected abstract void initializeListeners();

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
        // automatically handle clicks on the Home/Up button, so long
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

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(String message) {
        // todo implement
    }

    @Override
    public void stopLoading() {
        // todo implement
    }

    @Override
    public SharedPreferences getSharedPreferences(String name) {
        return this.getSharedPreferences(name, MODE_PRIVATE);
    }
}
