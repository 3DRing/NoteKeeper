package com.ringov.notekeeper.presenter.note_list;

import com.ringov.notekeeper.model.ModelManager;
import com.ringov.notekeeper.model.interfaces.NoteListModelAccess;
import com.ringov.notekeeper.view.interfaces.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.presenter.base.BasePresenter;
import com.ringov.notekeeper.view.interfaces.NoteListView;

import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */
public class NoteListPresenter extends BasePresenter<NoteListView, NoteListModelAccess>
        implements NoteListControl, NoteListModelControl{
    public NoteListPresenter(NoteListView view) {
        super(view);
        model = ModelManager.getNoteListModel(this, view);
    }

    @Override
    public void loadNoteList(ContextProvider contextProvider) {
        view.showLoading("");
        model.loadNoteList(contextProvider);
    }

    @Override
    public void deleteNote(int id, ContextProvider contextProvider) {
        view.stopLoading();
        model.deleteNote(id, contextProvider);
    }

    @Override
    public void sendNoteList(List<NoteEntry> notes) {
        view.stopLoading();
        view.showNoteList(notes);
    }

    @Override
    public void deletedSuccessfully(boolean success) {
        view.stopLoading();
        if(success) {
            view.showMessage("Successful deletion");
            view.update();
        }else{
            view.showMessage("Deletion failed");
        }
    }
}
