package com.example.crud_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String NAMA_DATABASE = "akademik_db";
    private static final String NAMA_TABEL = "mahasiswa";

    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAMA";
    private static final String COL_3 = "FAKULTAS";
    private static final String COL_4 = "JURUSAN";
    private static final String COL_5 = "SEMESTER";

    public SQLiteHelper(@Nullable Context context) {
        super(context, NAMA_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+NAMA_TABEL+" (" +
                COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2+ " TEXT, " +
                COL_3+ " TEXT, " +
                COL_4+ " TEXT, " +
                COL_5+ " TEXT" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NAMA_TABEL);
    }

    public boolean insertData(String nama, String fakultas, String jurusan, String semester){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COL_2,nama);
        values.put(COL_3,fakultas);
        values.put(COL_4,jurusan);
        values.put(COL_5,semester);
        long result = db.insert(NAMA_TABEL, null, values);
        return result != -1;

    }

    public Cursor getDataAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+ NAMA_TABEL, null);
    }

    public boolean updateData(String id, String nama, String fakultas, String jurusan, String semester){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_1,id);
        values.put(COL_2,nama);
        values.put(COL_3,fakultas);
        values.put(COL_4,jurusan);
        values.put(COL_5,semester);

        db.update(NAMA_TABEL,values,COL_1+" = ? ",new String[]{id});
        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(NAMA_TABEL,COL_1+" = ?",new String[]{id});
    }

}
