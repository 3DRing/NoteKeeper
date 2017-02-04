package com.ringov.notekeeper.presenter.base;

import com.ringov.notekeeper.view.interfaces.BaseView;

/**
 * Created by Сергей on 04.02.2017.
 */

public class BasePresenter<View extends BaseView> implements BaseControl{

    protected View view;

    public BasePresenter(View view) {
        this.view = view;
    }

}
