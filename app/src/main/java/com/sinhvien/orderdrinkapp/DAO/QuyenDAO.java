package com.sinhvien.orderdrinkapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.Database.MyDatabase;

public class QuyenDAO {

    SQLiteDatabase database;
    public QuyenDAO(Context context){
        MyDatabase createDatabase = new MyDatabase(context);
        database = createDatabase.open();
    }

    public void ThemQuyen(String tenquyen){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabase.TBL_QUYEN_TENQUYEN,tenquyen);
        database.insert(MyDatabase.TBL_QUYEN,null,contentValues);
    }
    

    public String LayTenQuyenTheoMa(int maquyen){
        String tenquyen ="";
        String query = "SELECT * FROM "+ MyDatabase.TBL_QUYEN+" WHERE "+ MyDatabase.TBL_QUYEN_MAQUYEN+" = "+maquyen;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tenquyen = cursor.getString(cursor.getColumnIndex(MyDatabase.TBL_QUYEN_TENQUYEN));
            cursor.moveToNext();
        }
        return tenquyen;
    }
}
