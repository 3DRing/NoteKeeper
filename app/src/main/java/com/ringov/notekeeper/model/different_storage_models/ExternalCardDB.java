package com.ringov.notekeeper.model.different_storage_models;

import android.os.Environment;
import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.presenter.android_relations_providers.ContextProvider;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Сергей on 06.02.2017.
 */

public class ExternalCardDB implements DBInterface {

    private static final String ALL_DATA_PATH = "/Android/data";
    private static final String APPLICATION_PATH = "/com.ringov.notekeeper";
    private static final String DB_FILE_NAME = "notekeeper";
    private static final String DB_FILE_EXTENTION = ".txt";

    private boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private String readFromFile(String fileName){
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + ALL_DATA_PATH + APPLICATION_PATH;
        final File file = new File(dir, fileName + DB_FILE_EXTENTION);

        String data = "";

        try {
            FileInputStream fis = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                data = data + strLine;
            }
            in.close();
        } catch (IOException e) {
            return null;
        }
        return data;
    }

    // todo return meaningful values instead of "false" to understand what is the problem
    private boolean writeToFile(String fileName, String body) {
        FileOutputStream fos = null;

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            return false;
        }

        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + ALL_DATA_PATH + APPLICATION_PATH;

        final File dir = new File(dirPath);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return false;
            }
        }

        final File myFile = new File(dir, fileName + DB_FILE_EXTENTION);

        try {
            if (!myFile.exists()) {
                myFile.createNewFile();
            }

            fos = new FileOutputStream(myFile);

            fos.write(body.getBytes());
            fos.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<NoteEntry> getNoteList(ContextProvider contextProvider) {
        String rawNote = readFromFile(DB_FILE_NAME);
        return new LinkedList<>();
    }

    @Override
    public boolean addNote(NoteEntry note, ContextProvider contextProvider) {
        String rawNote = "test";
        boolean result = writeToFile(DB_FILE_NAME, rawNote);
        return result;
    }

    @Override
    public boolean editNote(NoteEntry note, ContextProvider contextProvider) {
        return false;
    }

    @Override
    public boolean deleteNote(int id, ContextProvider contextProvider) {
        return false;
    }

    @Override
    public NoteEntry loadNote(int id, ContextProvider contextProvider) {

        return NoteEntry.EMPTY_NOTE;
    }
}
