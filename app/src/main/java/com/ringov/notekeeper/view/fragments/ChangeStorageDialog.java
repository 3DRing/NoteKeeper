package com.ringov.notekeeper.view.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.ringov.notekeeper.R;
import com.ringov.notekeeper.StorageMap;

import java.io.Serializable;

/**
 * Created by Сергей on 04.02.2017.
 */

public class ChangeStorageDialog extends DialogFragment {

    private StorageTypeResultCallback callback;
    private int crtValue;
    private int newValue;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Bundle args = getArguments();
        crtValue = args.getInt("crt_storage_type");//todo remove hardcoded text
        callback = (StorageTypeResultCallback) args.get("callback"); //todo remove hardcoded text

        builder.setTitle(R.string.change_storage)
                .setSingleChoiceItems(R.array.storage_list, crtValue,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChangeStorageDialog.this.newValue = which;
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(crtValue != newValue) {
                            callback.changeStorageType(StorageMap.getStorageType(newValue));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // nothing
                    }
                });

        return builder.create();
    }

    public interface StorageTypeResultCallback extends Serializable{
        void changeStorageType(StorageMap.STORAGE_TYPE type);
    }
}
