package com.ringov.notekeeper.presenter;

import com.ringov.notekeeper.view.activities.SingleNoteActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Сергей on 04.02.2017.
 */
public class NoteEntry implements Serializable{

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
