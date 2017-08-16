package com.metropolitan.cs330_dz05;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mare on 6/9/17.
 */

public class SecondActivity extends Activity {
    private ImageView logo;
    private TextView omiljeniPredmet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        logo = (ImageView) findViewById(R.id.logo);
        omiljeniPredmet = (TextView) findViewById(R.id.omiljeniPredmet);
        onLoad();
    }

    private void onLoad() {
        logo.setLongClickable(true);
        logo.setClickable(true);
        logo.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        createContextMenu(menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        menuIzbor(item);
        return true;
    }

    private void createContextMenu(ContextMenu menu) {
        menu.setHeaderTitle("Predmeti");
        menu.add("MA202 - Matematika 2");
        menu.add("CS330 - Razvoj mobilnih aplikacija");
        menu.add("IT255 - Web sistemi 1");
        menu.add("IT355 - Web sistemi 2");
        menu.add("IT320 - Savremene tehnoloske platforme");
    }

    private boolean menuIzbor(MenuItem item) {
        String tmp = "Vaš omiljeni predmet je: \n";
        omiljeniPredmet.setText(tmp + item.getTitle());
        return true;
    }
}