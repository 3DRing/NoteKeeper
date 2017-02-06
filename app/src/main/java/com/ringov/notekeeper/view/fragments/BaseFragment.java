package com.ringov.notekeeper.view.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.ringov.notekeeper.view.interfaces.BaseView;
import com.ringov.notekeeper.view.interfaces.ContextProvider;

/**
 * Created by Сергей on 04.02.2017.
 */

public abstract class BaseFragment extends Fragment implements ContextProvider{

    protected BaseView baseViewProvider;

    protected abstract void setBaseView(BaseView baseViewProvider);

    @Override
    public Context extractContext() {
        return getContext();
    }
}
