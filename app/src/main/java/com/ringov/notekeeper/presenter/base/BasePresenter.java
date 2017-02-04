package com.ringov.notekeeper.presenter.base;

import com.ringov.notekeeper.model.interfaces.BaseModelAccess;
import com.ringov.notekeeper.view.interfaces.BaseView;

/**
 * Created by Сергей on 04.02.2017.
 */

public abstract class BasePresenter<View extends BaseView, Model extends BaseModelAccess> implements BaseControl{

    protected View view;
    protected Model model;

    public BasePresenter(View view) {
        this.view = view;
    }

}
