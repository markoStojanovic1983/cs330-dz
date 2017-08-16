package com.metropolitan.cs330_dz08;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;
    IntentFilter intentFilter;
    private TextView sadrzaj;


    /* Poziva se kada se aktivnost kreira. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sadrzaj = (TextView) findViewById(R.id.editText);

        sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //—-filter prijema SMS poruka—-
        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        //---registrovanje primaoca---
        //registerReceiver(intentReceiver, intentFilter);
    }

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //—-prikazuje primljeni SMS u TextView—-IntentFilter filter = new IntentFilter();

            TextView SMSes = (TextView) findViewById(R.id.editText);
            SMSes.setText(intent.getExtras().getString("sms"));


        }
    };

    @Override
    public void onResume() {
        super.onResume();

        //---registrovanje primaoca---
        registerReceiver(intentReceiver, intentFilter);

        //---kreira BroadcastReceiver kada je SMS poslat---
        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS prosleđen",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generička greška",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "Nema usluge",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio isključen",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        //---kreira BroadcastReceiver kada SMS dostavljen---
        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS dostavljen",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS nije dostavljen",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        //---registruje dva BroadcastReceiver - a---
        registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));
        registerReceiver(smsSentReceiver, new IntentFilter(SENT));
    }

    @Override
    public void onPause() {
        super.onPause();

        //---odjavljuje primaoca---
        unregisterReceiver(intentReceiver);

		/*//---odjavljuje dva BroadcastReceiver-a---
        unregisterReceiver(smsSentReceiver);
		unregisterReceiver(smsDeliveredReceiver);    */
    }

    /* @Override
     protected void onDestroy() {
         super.onDestroy();

         //---odjavljivanje primaoca---
         unregisterReceiver(intentReceiver);
     }
 */

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void onSMSClick(View v) {

        try {
            Uri uri = Uri.parse("smsto:" + 5556);
            // No permisison needed
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
            // Set the message to be sent
            smsIntent.putExtra("sms_body", sadrzaj.getText().toString());
            startActivity(smsIntent);

        } catch (Exception e) {
            Toast.makeText(this,
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void onEmailClick(View v) {
        try {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_EMAIL, "stojanovicm994@gmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Proba");
            intent.putExtra(Intent.EXTRA_TEXT, sadrzaj.getText().toString());

            startActivity(Intent.createChooser(intent, "Send Email"));

        } catch (Exception e) {
            Toast.makeText(this,
                    "Email faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}

