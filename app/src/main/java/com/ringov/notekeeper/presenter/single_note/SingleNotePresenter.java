package com.ringov.notekeeper.presenter.single_note;

import com.ringov.notekeeper.model.ModelManager;
import com.ringov.notekeeper.model.interfaces.SingleNoteModelAccess;
import com.ringov.notekeeper.view.interfaces.ContextProvider;
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
        model = ModelManager.getSingleNoteModel(this, view);
    }

    @Override
    public void loadNote(int id, ContextProvider contextProvider) {
        view.showLoading("");
        model.loadNote(id, contextProvider);
    }

    @Override
    public void commitNote(NoteEntry entry, boolean creating, ContextProvider contextProvider) {
        if(creating){
            if(entry.getTitle() == null || entry.getTitle().equals("")){
                view.showMessage("You have to fill in the title"); // todo remove hardcoded text
                return;
            }
        }
        view.showLoading("");
        model.commitNote(entry, creating, contextProvider);
    }

    @Override
    public void createdSuccessfully(boolean success) {
        showSuccess(success);
    }

    @Override
    public void editedSuccessfully(boolean success) {
        showSuccess(success);
    }

    @Override
    public void showNote(NoteEntry note) {
        view.stopLoading();
        if(!note.equals(NoteEntry.EMPTY_NOTE)){
            view.showNote(note);
        }else{
            view.showMessage("Note not found");
        }
    }

    private void showSuccess(boolean success){
        if(success){
            view.successCommit();
        }else{
            view.failedCommit();
        }
    }
}
