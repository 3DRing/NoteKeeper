package com.ringov.notekeeper.presenter.single_note.sms_handling;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ringov.notekeeper.R;
import com.ringov.notekeeper.model.ModelManager;
import com.ringov.notekeeper.model.interfaces.SingleNoteModelAccess;
import com.ringov.notekeeper.presenter.NoteEntry;
import com.ringov.notekeeper.view.interfaces.ContextProvider;
import com.ringov.notekeeper.presenter.single_note.SingleNoteControl;
import com.ringov.notekeeper.view.activities.HomeActivity;

/**
 * Created by Сергей on 05.02.2017.
 */

public class SmsService extends Service implements SingleNoteControl{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SmsReceiver.SMS sms = (SmsReceiver.SMS)intent.getSerializableExtra("sms"); // todo remove hardcoded text

        // sms saving
        String title = "Message from " + sms.getSender();  // todo remove hardcoded text
        NoteEntry note = new NoteEntry(-1, title,sms.getDate());
        note.setText(sms.getText());

        commitNote(note, true, new ContextProvider() {
            @Override
            public Context extractContext() {
                return getApplicationContext();
            }
        });

        showNotification(note);
        return START_STICKY;
    }

    private void showNotification(NoteEntry note) {
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, HomeActivity.class), 0);
        Context context = getApplicationContext();

        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle(note.getTitle())
                .setContentText(note.getText())
                .setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.getNotification();
        notificationManager.notify(R.mipmap.ic_launcher, notification);
    }

    @Override
    public void loadNote(int id, ContextProvider contextProvider) {
        // not used
        // todo - separate interfaces to avoid this method appear here
    }

    @Override
    public void commitNote(NoteEntry entry, boolean creating, ContextProvider contextProvider) {
        SingleNoteModelAccess model = ModelManager.getSingleNoteModel(null, contextProvider);
        model.commitNote(entry,creating,contextProvider);
    }
}
