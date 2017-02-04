package com.ringov.notekeeper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Сергей on 04.02.2017.
 */
public class NoteEntry implements Serializable{

    private int id;
    private String title;
    private Date date;
    private String text;

    public NoteEntry(int id, String title, Date date){
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFormattedDate(){
        SimpleDateFormat format = new SimpleDateFormat("d MMM yyyy, HH:mm");
        return format.format(date).toString();
    }

    public String getText() {
        return text;
    }
    public void setText(String text){
        this.text = text;
    }
}
