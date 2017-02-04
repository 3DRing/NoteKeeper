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

/**
 * Created by Сергей on 04.02.2017.
 */

public class ChangeStorageDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.change_storage)
                .setItems(StorageMap.getStorageTypeArrayRes(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), StorageMap.getStorageType(which).toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
