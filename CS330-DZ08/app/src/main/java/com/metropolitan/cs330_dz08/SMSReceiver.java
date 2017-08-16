package com.metropolitan.cs330_dz08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by mare on 6/10/17.
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //---preuzimanje prosleđene SMS poruke---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "SMS iz ";
        if (bundle != null) {
            //---učitavanje primljene SMS poruke---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (i == 0) {
                    //---preuzimanje podataka o pošiljaocu---
                    str += msgs[i].getOriginatingAddress();
                    str += ": ";
                }
                //---preuzimanje tela poruke---
                str += msgs[i].getMessageBody().toString();
            }

           /* //---zaustavljanje slanja/primanja---
            this.abortBroadcast();*/
            //---pokretanje SMSActivity---
            Intent mainActivityIntent = new Intent(context, MainActivity.class);
            mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivityIntent);
            //---Slanje namere BroadcastIntent za ažuriranje SMS iz aktivnosti---
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("SMS_RECEIVED_ACTION");
            broadcastIntent.putExtra("sms", str);
            context.sendBroadcast(broadcastIntent);
            abortBroadcast();
        }
        abortBroadcast();
    }
}



