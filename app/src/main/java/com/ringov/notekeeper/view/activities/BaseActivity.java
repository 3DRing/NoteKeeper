package com.ringov.notekeeper.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ringov.notekeeper.view.interfaces.BaseView;

/**
 * Created by Сергей on 06.02.2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    protected abstract void bindViews();
    protected abstract void initializeListeners();

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
