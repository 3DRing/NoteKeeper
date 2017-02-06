package com.ringov.notekeeper.presenter.sms_handling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Сергей on 05.02.2017.
 */

public class SmsReceiver extends BroadcastReceiver {

    public static class SMS implements Serializable{

        private String sender;
        private Date date;
        private String text;

        public SMS(String sender, Date date, String text){
            this.sender = sender;
            this.date = date;
            this.text = text;
        }

        public String getSender() {
            return sender;
        }

        public Date getDate() {
            return date;
        }

        public String getText() {
            return text;
        }
    }

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = null;
        String text = "";
        if (bundle != null)
        {
            Object[] pdus = (Object[]) bundle.get("pdus");
            messages = new SmsMessage[pdus.length];
            for (int i=0; i<messages.length; i++)

            {
                messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                text += messages[i].getMessageBody().toString();
                text += "\n";
            }

            String sender = messages[0].getOriginatingAddress();
            Date date = new Date();

            String adr = messages[0].getOriginatingAddress();
            String pseudo = messages[0].getPseudoSubject();

            SMS sms = new SMS(sender, date, text);

            Intent toServiceIntent = new Intent(context, SmsService.class);
            toServiceIntent.putExtra("sms", sms); // todo remove hardcoded text
            context.startService(toServiceIntent);
        }
    }
}
