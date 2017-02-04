package com.ringov.notekeeper.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.ringov.notekeeper.R;
import com.ringov.notekeeper.StorageMap;

import java.io.Serializable;

/**
 * Created by Сергей on 04.02.2017.
 */

public class ChangeStorageDialog extends DialogFragment {

    private StorageTypeResultCallback callback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Bundle args = getArguments();
        callback = (StorageTypeResultCallback) args.get("callback"); //todo remove hardcoded text

        builder.setTitle(R.string.change_storage)
                .setItems(StorageMap.getStorageTypeArrayRes(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        callback.changeStorageType(StorageMap.getStorageType(which));
                    }
                });

        return builder.create();
    }

    public interface StorageTypeResultCallback extends Serializable{
        void changeStorageType(StorageMap.STORAGE_TYPE type);
    }
}
