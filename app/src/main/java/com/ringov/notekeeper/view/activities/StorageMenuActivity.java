package com.ringov.notekeeper.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;

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

    private void setActionBarCheckboxChecked(MenuItem it, boolean checked)
    {
        if (it == null)
            return;

        it.setChecked(checked);

        // Since it is shown as an action, and not in the sub-menu we have to manually set the icon too.
        CheckBox cb = (CheckBox)it.getActionView().findViewById(R.id.sms_fetching_checkbox);
        if (cb != null)
            cb.setChecked(checked);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

        final MenuItem logItem = menu.findItem(R.id.sms_fetching_checkbox);
        setActionBarCheckboxChecked(logItem,settingsControl.isSmsFetchingEnabled(this));
        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        return super.onMenuOpened(featureId, menu);
    }

    private CheckBox checkBox;

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
            b.putInt("crt_storage_type",StorageMap.getStorageId(settingsControl.getStorageType(this)));
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
        if(id == R.id.sms_fetching_checkbox){
            item.setChecked(!item.isChecked());
            settingsControl.smsFetchingEnabled(item.isChecked(), this);
        }

        return super.onOptionsItemSelected(item);
    }
}
