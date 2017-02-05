package com.ringov.notekeeper.view.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.ringov.notekeeper.R;

import java.io.Serializable;

/**
 * Created by Сергей on 05.02.2017.
 */

public class DeleteRecordDialog extends DialogFragment {

    private DeleteCallback callback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Bundle args = getArguments();
        callback = (DeleteRecordDialog.DeleteCallback) args.get("callback"); //todo remove hardcoded text

        builder.setMessage(R.string.delete_note_dialog)
                .setPositiveButton(R.string.ok_delete_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        callback.deleteNote();
                    }
                })
                .setNegativeButton(R.string.cancel_delete_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteRecordDialog.this.dismiss();
                    }
                });
        return builder.create();
    }

    public interface DeleteCallback extends Serializable {
        void deleteNote();
    }
}
