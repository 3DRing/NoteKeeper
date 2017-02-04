package com.ringov.notekeeper.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ringov.notekeeper.R;
import com.ringov.notekeeper.view.ChangeStorageDialog;
import com.ringov.notekeeper.view.interfaces.BaseView;

/**
 * Created by Сергей on 04.02.2017.
 */

public abstract class StorageMenuActivity extends AppCompatActivity implements BaseView{

    protected abstract void bindViews();
    protected abstract void initializeListeners();

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
}
