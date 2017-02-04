package com.ringov.notekeeper.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.R;
import com.ringov.notekeeper.view.interfaces.EntryClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 04.02.2017.
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListHolder> {
    private static final String TAG = "NoteListAdapter";
    private List<NoteEntry> notes = new ArrayList<>();
    private EntryClick click;

    public NoteListAdapter(EntryClick click){
        this.click = click;
    }

    @Override
    public NoteListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_note_list_entry, parent, false);

        return new NoteListHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteListHolder holder, int position) {
        final NoteEntry crtNote = notes.get(position);
        holder.tvTitle.setText(crtNote.getTitle());
        holder.tvDate.setText(crtNote.getFormattedDate());

        holder.llEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.handle(crtNote);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteListHolder extends RecyclerView.ViewHolder {

        private LinearLayout llEntry;
        public TextView tvTitle;
        public TextView tvDate;

        public NoteListHolder(View itemView) {
            super(itemView);

            llEntry = (LinearLayout) itemView.findViewById(R.id.ll_entry);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

    public void addAll(List<NoteEntry> noteList){
        notes.addAll(noteList);
        notifyDataSetChanged();
    }
}
