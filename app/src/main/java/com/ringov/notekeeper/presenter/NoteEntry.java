package com.ringov.notekeeper.presenter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Сергей on 04.02.2017.
 */
public class NoteEntry implements Serializable{

    public static final NoteEntry EMPTY_NOTE = new NoteEntry(-1, "", new Date()); // -1 means this entry should be created
    private int id;
    protected String title;
    protected Date date;
    protected String text;

    private final static SimpleDateFormat format = new SimpleDateFormat("d MMM yyyy, HH:mm");

    public NoteEntry(int id, String title, Date date){
        this.id = id;
        this.title = title;
        this.date = date;
        this.text = "";
    }

    public NoteEntry(NoteEntry note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.date = note.getDate();
        this.text = note.getText();
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFormattedDate(){

        return format.format(date).toString();
    }

    public String getText() {
        return text;
    }
    public void setText(String text){
        this.text = text;
    }

    public Date getDate() {
        return date;
    }
}
