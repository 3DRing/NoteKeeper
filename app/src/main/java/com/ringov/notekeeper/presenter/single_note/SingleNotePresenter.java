package com.ringov.notekeeper.presenter.single_note;

import com.ringov.notekeeper.presenter.base.BasePresenter;
import com.ringov.notekeeper.view.interfaces.SingleNoteView;

/**
 * Created by Сергей on 04.02.2017.
 */
public class SingleNotePresenter extends BasePresenter<SingleNoteView> implements SingleNoteControl {
    public SingleNotePresenter(SingleNoteView view) {
        super(view);
    }
}
