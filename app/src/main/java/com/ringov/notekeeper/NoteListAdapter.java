package com.ringov.notekeeper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListHolder> {
    private static final String TAG = "NoteListAdapter";
    private List<NoteEntry> notes = new ArrayList<>();

    @Override
    public NoteListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_note_list_entry, parent, false);

        return new NoteListHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteListHolder holder, int position) {
        NoteEntry crtNote = notes.get(position);
        holder.tvTitle.setText(crtNote.getTitle());
        holder.tvDate.setText(crtNote.getFormattedDate());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteListHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvDate;

        public NoteListHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

    public void addAll(List<NoteEntry> noteList){
        notes.addAll(noteList);
        notifyDataSetChanged();
    }
}
