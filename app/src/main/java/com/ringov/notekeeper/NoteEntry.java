package com.ringov.notekeeper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Сергей on 04.02.2017.
 */
public class NoteEntry {

    private String title;
    private Date date;

    public NoteEntry(String title, Date date){
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getFormattedDate(){
        SimpleDateFormat format = new SimpleDateFormat("d MMM yyyy, HH:mm");
        return format.format(date).toString();
    }
}
