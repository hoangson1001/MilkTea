package com.sinhvien.orderdrinkapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.DTO.LoaiMonDTO;
import com.sinhvien.orderdrinkapp.Database.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class LoaiMonDAO {

    SQLiteDatabase database;
    public LoaiMonDAO(Context context){
        MyDatabase createDatabase = new MyDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemLoaiMon(LoaiMonDTO loaiMonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabase.TBL_LOAIMON_TENLOAI,loaiMonDTO.getTenLoai());
        contentValues.put(MyDatabase.TBL_LOAIMON_HINHANH,loaiMonDTO.getHinhAnh());
        long ktra = database.insert(MyDatabase.TBL_LOAIMON,null,contentValues);

        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean XoaLoaiMon(int maloai){
        long ktra = database.delete(MyDatabase.TBL_LOAIMON, MyDatabase.TBL_LOAIMON_MALOAI+ " = " +maloai
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }

    public boolean SuaLoaiMon(LoaiMonDTO loaiMonDTO,int maloai){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabase.TBL_LOAIMON_TENLOAI,loaiMonDTO.getTenLoai());
        contentValues.put(MyDatabase.TBL_LOAIMON_HINHANH,loaiMonDTO.getHinhAnh());
        long ktra = database.update(MyDatabase.TBL_LOAIMON,contentValues
                , MyDatabase.TBL_LOAIMON_MALOAI+" = "+maloai,null);
        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }

    public List<LoaiMonDTO> LayDSLoaiMon(){
        List<LoaiMonDTO> loaiMonDTOList = new ArrayList<LoaiMonDTO>();
        String query = "SELECT * FROM " + MyDatabase.TBL_LOAIMON;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            LoaiMonDTO loaiMonDTO = new LoaiMonDTO();
            loaiMonDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(MyDatabase.TBL_LOAIMON_MALOAI)));
            loaiMonDTO.setTenLoai(cursor.getString(cursor.getColumnIndex(MyDatabase.TBL_LOAIMON_TENLOAI)));
            loaiMonDTO.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(MyDatabase.TBL_LOAIMON_HINHANH)));
            loaiMonDTOList.add(loaiMonDTO);

            cursor.moveToNext();
        }
        return loaiMonDTOList;
    }

    public LoaiMonDTO LayLoaiMonTheoMa(int maloai){
        LoaiMonDTO loaiMonDTO = new LoaiMonDTO();
        String query = "SELECT * FROM " + MyDatabase.TBL_LOAIMON+" WHERE "+ MyDatabase.TBL_LOAIMON_MALOAI+" = "+maloai;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            loaiMonDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(MyDatabase.TBL_LOAIMON_MALOAI)));
            loaiMonDTO.setTenLoai(cursor.getString(cursor.getColumnIndex(MyDatabase.TBL_LOAIMON_TENLOAI)));
            loaiMonDTO.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(MyDatabase.TBL_LOAIMON_HINHANH)));

            cursor.moveToNext();
        }
        return loaiMonDTO;
    }

}
