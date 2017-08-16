package com.metropolitan.cs330_dz06;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mare on 6/11/17.
 */

public class TableControllerStudent extends DatabaseHandler {

    private String BROJ_INDEKSA = "broj_indeksa";
    private String IME = "ime";
    private String BROJ_BODOVA = "broj_bodova";
    private static final String TABLE_NAME = "studenti";

    SQLiteDatabase db = this.getWritableDatabase();

    public TableControllerStudent(Context context) {
        super(context);
    }

    public boolean create(ObjectStudent objectStudent) {

        ContentValues values = new ContentValues();

        values.put(BROJ_INDEKSA, objectStudent.getBroj_indeksa());
        values.put(IME, objectStudent.getIme());
        values.put(BROJ_BODOVA, objectStudent.getBroj_bodova());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert(TABLE_NAME, null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {

        String sql = "SELECT * FROM " + TABLE_NAME;
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<ObjectStudent> read() {

        List<ObjectStudent> recordsList = new ArrayList<ObjectStudent>();

        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + BROJ_BODOVA + " DESC";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int brojIndeksa = Integer.parseInt(cursor.getString(cursor.getColumnIndex(BROJ_INDEKSA)));
                String ime = cursor.getString(cursor.getColumnIndex(IME));
                int brojBodova = Integer.parseInt(cursor.getString(cursor.getColumnIndex(BROJ_BODOVA)));

                ObjectStudent objectStudent = new ObjectStudent(brojIndeksa, ime, brojBodova);


                recordsList.add(objectStudent);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public ObjectStudent readSingleRecord(int broj_indeksa) {

        ObjectStudent objectStudent = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + BROJ_INDEKSA + "=" + broj_indeksa;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            int brojIndeksa = Integer.parseInt(cursor.getString(cursor.getColumnIndex(BROJ_INDEKSA)));
            String ime = cursor.getString(cursor.getColumnIndex(IME));
            int brojBodova = Integer.parseInt(cursor.getString(cursor.getColumnIndex(BROJ_BODOVA)));

            objectStudent = new ObjectStudent(brojIndeksa, ime, brojBodova);

        }

        cursor.close();
        db.close();

        return objectStudent;
    }

    public boolean update(ObjectStudent objectStudent, int studentID) {

        ContentValues values = new ContentValues();

        values.put(BROJ_INDEKSA, objectStudent.getBroj_indeksa());
        values.put(IME, objectStudent.getIme());
        values.put(BROJ_BODOVA, objectStudent.getBroj_bodova());

        String where = BROJ_INDEKSA + " = ?";

        String[] whereArgs = {Integer.toString(studentID)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update(TABLE_NAME, values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }

    public boolean deleteContact(int StudentID) {
        return db.delete(TABLE_NAME, BROJ_INDEKSA + "=" + StudentID, null) > 0;
    }
}