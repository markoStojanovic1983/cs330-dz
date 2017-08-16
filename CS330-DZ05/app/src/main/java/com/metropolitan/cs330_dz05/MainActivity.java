package com.metropolitan.cs330_dz05;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent i = new Intent("android.intent.action.VIEW");
        if (id == R.id.univerzitet) {
            i.setData(Uri.parse("http://www.metropolitan.edu.rs"));
            startActivity(i);
            return true;
        } else if (id == R.id.fit) {
            i.setData(Uri.parse("http://www.metropolitan.edu.rs/osnovne-studije/fakultet-informacionih-tehnologija/"));
            startActivity(i);
            return true;
        } else if (id == R.id.fam) {
            i.setData(Uri.parse("http://www.metropolitan.edu.rs/osnovne-studije/fakultet-za-menadzment/"));
            startActivity(i);
            return true;
        } else if (id == R.id.fdu) {
            i.setData(Uri.parse("http://www.metropolitan.edu.rs/fakultet-digitalnih-umetnosti-2/"));
            startActivity(i);
            return true;
        } else if (id == R.id.next) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}