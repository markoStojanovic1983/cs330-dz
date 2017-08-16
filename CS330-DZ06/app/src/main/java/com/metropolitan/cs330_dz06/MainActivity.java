package com.metropolitan.cs330_dz06;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Button btnKreirajStudenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnKreirajStudenta = (Button) findViewById(R.id.btnKreirajStudenta);
        btnKreirajStudenta.setOnClickListener(new OnClickListenerCreateStudent());


        try {
            String destPath = "/data/data/" + getPackageName() +
                    "/databases";
            File f = new File(destPath);
            if (!f.exists()) {
                f.mkdirs();
                f.createNewFile();
                //---kopira db iz assets foldera u databases folder---
                CopyDB(getBaseContext().getAssets().open("ispit"),
                        new FileOutputStream(destPath + "/ispit"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        readRecords();
        countRecords();
    }

    public void CopyDB(InputStream inputStream,
                       OutputStream outputStream) throws IOException {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    public void countRecords() {
        int brojacStudenata = new TableControllerStudent(this).count();

        TextView brojStudenata = (TextView) findViewById(R.id.editTextBrojStudenata);
        brojStudenata.setText(brojacStudenata + " studenta se nalaze u bazi.");
    }

    public void readRecords() {

        LinearLayout linearLayoutStudenti = (LinearLayout) findViewById(R.id.linearLayoutStudenti);
        linearLayoutStudenti.removeAllViews();

        List<ObjectStudent> studenti = new TableControllerStudent(this).read();

        if (studenti.size() > 0) {

            for (ObjectStudent obj : studenti) {

                int broj_indeksa = obj.getBroj_indeksa();
                String ime = obj.getIme();
                int broj_bodova = obj.getBroj_bodova();

                String textViewContents = "Broj indeksa: " + broj_indeksa
                        + " Ime: " + ime
                        + " Broj bodova: " + broj_bodova;

                TextView textViewStudent = new TextView(this);
                textViewStudent.setPadding(0, 10, 0, 10);
                textViewStudent.setText(textViewContents);
                textViewStudent.setTag(Integer.toString(broj_indeksa));
                textViewStudent.setOnLongClickListener(new OnLongClickListenerStudentRecord());

                linearLayoutStudenti.addView(textViewStudent);

            }
        } else {

            TextView nemaStudenata = new TextView(this);
            nemaStudenata.setPadding(16, 16, 16, 16);
            nemaStudenata.setText("U bazi podataka nema informacija o studentima");

            linearLayoutStudenti.addView(nemaStudenata);
        }
    }
}
