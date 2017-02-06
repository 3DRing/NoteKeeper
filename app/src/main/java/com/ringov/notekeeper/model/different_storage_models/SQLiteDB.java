package com.ringov.notekeeper.model.different_storage_models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ringov.notekeeper.view.interfaces.ContextProvider;
import com.ringov.notekeeper.presenter.NoteEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */

public class SQLiteDB implements DBInterface {

    private static SQLiteHelper helper;

    public SQLiteHelper getInstance(ContextProvider contextProvider){
        if(helper != null){
            return helper;
        }else{
            return new SQLiteHelper(contextProvider);
        }
    }

    @Override
    public List<NoteEntry> getNoteList(ContextProvider contextProvider) {
        List<NoteEntry> notes = getInstance(contextProvider).getNoteList();
        return notes;
    }

    @Override
    public boolean addNote(NoteEntry note, ContextProvider contextProvider) {
        return getInstance(contextProvider).addNote(note);
    }

    @Override
    public boolean editNote(NoteEntry note, ContextProvider contextProvider) {
        return getInstance(contextProvider).editNote(note);
    }

    @Override
    public boolean deleteNote(int id, ContextProvider contextProvider) {
        return getInstance(contextProvider).deleteNote(id);
    }

    @Override
    public NoteEntry loadNote(int id, ContextProvider contextProvider) {
        return getInstance(contextProvider).loadNote(id);
    }

    private class SQLiteHelper extends SQLiteOpenHelper{
        private static final int VERSION = 1;
        private static final String DB_NAME = "notekeeper.db";
        final String TABLE_NAME = "notes";
        final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "( _id INTEGER PRIMARY KEY , title TEXT, " +
                " date INTEGER, text TEXT)";
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        private ContextProvider contextProvider;

        public SQLiteHelper(ContextProvider contextProvider) {
            super(contextProvider.extractContext(), DB_NAME, null, VERSION);
            this.contextProvider = contextProvider;
        }

        @Override
        public void onCreate(android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }

        public boolean addNote(NoteEntry note){
            SQLiteDatabase db = getWritableDatabase();

            ContentValues insertValues = new ContentValues();
            insertValues.put("_id", note.getId());
            insertValues.put("title", note.getTitle());
            insertValues.put("date", note.getDate().getTime());
            insertValues.put("text", note.getText());

            long result = db.insert(TABLE_NAME, null, insertValues);

            db.close();
            return result != -1; // false if inserting was failed
        }

        public boolean editNote(NoteEntry note) {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues updatedValues = new ContentValues();
            int id = note.getId();

            updatedValues.put("title", note.getTitle());
            updatedValues.put("date", note.getDate().getTime());
            updatedValues.put("text", note.getText());

            int result = db.update(TABLE_NAME, updatedValues, "_id="+id, null);

            return result != -1; // false if inserting was failed
        }

        private List<NoteEntry> getEntries(String clause){
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor;
            if(clause == null || clause.equals("")){
                cursor = db.rawQuery("select * from " + TABLE_NAME,null);
            }else{
                cursor = db.rawQuery("select * from " + TABLE_NAME + " WHERE " + clause,null);
            }

            List<NoteEntry> notes = new ArrayList<>();
            if(cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    long date = cursor.getLong(cursor.getColumnIndex("date"));
                    String text = cursor.getString(cursor.getColumnIndex("text"));

                    Date properDate = new Date(date);
                    NoteEntry note = new NoteEntry(id, title, properDate);
                    note.setText(text);

                    notes.add(note);

                    cursor.moveToNext();
                }
            }
            db.close();
            return notes;
        }

        public List<NoteEntry> getNoteList(){
            return getEntries("");
        }

        public boolean deleteNote(int id) {
            SQLiteDatabase db = getWritableDatabase();
            int result = db.delete(TABLE_NAME, "_id = " + id, null);
            db.close();
            return result != -1; // false if inserting was failed
        }

        public NoteEntry loadNote(int id) {
            List<NoteEntry> notes = getEntries("_id = " + id);
            if(notes.size() == 0){
                // note with such id not found - unexpected behaviour
                return NoteEntry.EMPTY_NOTE;
            }
            if(notes.size() != 1){
                // impossible case, but anyway, let's return [0] record
                return notes.get(0);
            }else{
                // normal behaviour
                return notes.get(0);
            }
        }
    }
}
