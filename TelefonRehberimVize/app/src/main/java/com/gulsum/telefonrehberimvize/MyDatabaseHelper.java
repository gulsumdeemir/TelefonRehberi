package com.gulsum.telefonrehberimvize;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "TelefonRehberim.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "telefon_rehberim";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_NUMBER = "number";

     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_SURNAME + " TEXT, " +
                        COLUMN_NUMBER + " TEXT); ";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    void kisiEkle(String name, String surname, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SURNAME, surname);
        cv.put(COLUMN_NUMBER, number);
        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Hata", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eklendi", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }
    void updateData(String row_id, String name, String surname, String number){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(COLUMN_NAME, name);
         cv.put(COLUMN_SURNAME, surname);
         cv.put(COLUMN_NUMBER, number);

         long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
         if(result == -1){
             Toast.makeText(context, "Güncelleme Başarısız", Toast.LENGTH_SHORT).show();
         }else{
             Toast.makeText(context, "Güncelleme Başarılı", Toast.LENGTH_SHORT).show();
         }

    }
    void deleteOneRow(String row_id){
         SQLiteDatabase db= this.getWritableDatabase();
         long result = db.delete(TABLE_NAME, "_id=?",new String[]{row_id});
         if(result == -1){
             Toast.makeText(context, "Hata", Toast.LENGTH_SHORT).show();

         }else{
             Toast.makeText(context, "Kişi Silindi", Toast.LENGTH_SHORT).show();
         }

    }
    void deleteAllData(){
         SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("DELETE FROM " + TABLE_NAME);

    }
}
