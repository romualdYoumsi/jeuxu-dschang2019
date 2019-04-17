package com.example.jeuxu.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.jeuxu.Classe.Client;
import com.example.jeuxu.Common.Common;

import java.util.ArrayList;
import java.util.List;

import static com.example.jeuxu.Common.Common.COL1;
import static com.example.jeuxu.Common.Common.COL2;
import static com.example.jeuxu.Common.Common.COL3;
import static com.example.jeuxu.Common.Common.COL4;
import static com.example.jeuxu.Common.Common.COL5;
import static com.example.jeuxu.Common.Common.COL6;
import static com.example.jeuxu.Common.Common.COL7;
import static com.example.jeuxu.Common.Common.COL8;
import static com.example.jeuxu.Common.Common.COL9;
import static com.example.jeuxu.Common.Common.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT,"
                + COL3 + " TEXT," + COL4 + " TEXT," + COL5 + " TEXT," + COL6 + " TEXT," + COL7 + " TEXT," + COL8 + " TEXT," + COL9 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String NOM, String PRENOM, String SEXE, String UNIVERSITE, String LOCALISE, String NUMERO, String EMAIL, String Token) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, NOM);
        contentValues.put(COL3, PRENOM);
        contentValues.put(COL4, SEXE);
        contentValues.put(COL5, UNIVERSITE);
        contentValues.put(COL6, LOCALISE);
        contentValues.put(COL7, NUMERO);
        contentValues.put(COL8, EMAIL);
        contentValues.put(COL9, Token);

        Log.d(TAG, "addData: adding " + NOM + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Client> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {COL1, COL2, COL3, COL4, COL5, COL6, COL7, COL8, COL9};
        qb.setTables(Common.TABLE_NAME);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Client> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.setID(cursor.getString(cursor.getColumnIndex("ID")));
                client.setName(cursor.getString(cursor.getColumnIndex("NOM")));
                client.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
                client.setToken(cursor.getString(cursor.getColumnIndex("TOKEN")));
                client.setSurname(cursor.getString(cursor.getColumnIndex("PRENOM")));
                client.setLocalite(cursor.getString(cursor.getColumnIndex("LOCALISE")));
                client.setSexe(cursor.getString(cursor.getColumnIndex("SEXE")));
                client.setDate_naissance(cursor.getString(cursor.getColumnIndex("NUMERO")));
                client.setID_univercity(cursor.getString(cursor.getColumnIndex("UNIVERSITE")));


                result.add(client);


            } while (cursor.moveToNext());
        }
        return result;

    }

}
