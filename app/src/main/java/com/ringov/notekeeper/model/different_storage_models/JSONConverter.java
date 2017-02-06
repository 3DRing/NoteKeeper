package com.ringov.notekeeper.model.different_storage_models;

import com.ringov.notekeeper.presenter.NoteEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 06.02.2017.
 */

public class JSONConverter {
    public static JSONArray fromNoteList(List<NoteEntry> list) throws JSONException {
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            array.put(fromNoteEntry(list.get(i)));
        }
        return array;
    }

    public static List<NoteEntry> toNoteList(JSONArray array) throws JSONException {
        int size = array.length();
        List<NoteEntry> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(toNoteEntry(array.getJSONObject(i)));
        }
        return list;
    }

    public static JSONObject fromNoteEntry(NoteEntry note) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id",note.getId());
        json.put("title",note.getTitle());
        json.put("date", note.getDate().getTime());
        json.put("text", note.getText());

        return json;
    }

    public static NoteEntry toNoteEntry(JSONObject json) throws JSONException {
        int id = json.getInt("id");
        String title = json.getString("title");
        long date = json.getLong("date");
        String text = json.getString("text");
        NoteEntry note = new NoteEntry(id,title,new Date(date));
        note.setText(text);
        return note;
    }

    public static String toString(JSONObject json){
        return json.toString();
    }

    public static JSONObject fromString(String str) throws JSONException {
        return new JSONObject(str);
    }

    public static JSONArray fromStringToJSONArray(String str) throws JSONException {
        return new JSONArray(str);
    }

    public static String toString(JSONArray array) {
        return array.toString();
    }
}
