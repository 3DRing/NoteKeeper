package com.ringov.notekeeper.presenter.single_note;

import com.ringov.notekeeper.model.ModelManager;
import com.ringov.notekeeper.model.interfaces.SingleNoteModelAccess;
import com.ringov.notekeeper.presenter.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.presenter.base.BasePresenter;
import com.ringov.notekeeper.view.interfaces.SingleNoteView;

/**
 * Created by Сергей on 04.02.2017.
 */
public class SingleNotePresenter extends BasePresenter<SingleNoteView, SingleNoteModelAccess>
        implements SingleNoteControl, SingleNoteModelControl{
    public SingleNotePresenter(SingleNoteView view) {
        super(view);
        model = ModelManager.getSingleNoteModel(this);
    }

    @Override
    public void commitNote(NoteEntry entry, boolean creating, ContextProvider contextProvider) {
        if(creating) {
            model.addNote(entry, contextProvider);
        }else{
            model.editNote(entry.getId(), contextProvider);
        }
    }

    @Override
    public void createdSuccessfully(boolean success) {
        showSuccess(success);
    }

    @Override
    public void editedSuccessfully(boolean success) {
        showSuccess(success);
    }

    private void showSuccess(boolean success){
        if(success){
            view.successCommit();
        }else{
            view.failedCommit();
        }
    }
}
