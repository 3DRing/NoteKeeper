package com.ringov.notekeeper.view.interfaces;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface BaseView {
    void showMessage(String message);
    void showLoading(String message);
    void stopLoading();
}
