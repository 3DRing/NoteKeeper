package com.ringov.notekeeper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Сергей on 04.02.2017.
 */

public class NoteListFragment extends Fragment {

    public static String TAG = "NoteListFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_list_fragment,container, false);
        return view;
    }


}
