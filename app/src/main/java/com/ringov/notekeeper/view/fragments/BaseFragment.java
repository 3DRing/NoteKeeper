package com.ringov.notekeeper.view.fragments;

import android.support.v4.app.Fragment;

import com.ringov.notekeeper.view.interfaces.BaseView;

/**
 * Created by Сергей on 04.02.2017.
 */

public abstract class BaseFragment extends Fragment {

    protected BaseView baseViewProvider;

    protected abstract void setBaseView(BaseView baseViewProvider);
}
