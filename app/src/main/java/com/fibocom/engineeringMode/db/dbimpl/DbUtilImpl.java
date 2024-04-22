package com.fibocom.engineeringMode.db.dbimpl;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.fibocom.engineeringMode.bean.GlobalContentValues;
import com.fibocom.engineeringMode.db.DBopenhelper;
import com.fibocom.engineeringMode.db.DbUtil;

public class DbUtilImpl implements DbUtil {

    @Override
    public void save(String result,String key,DBopenhelper dBopenhelper,int temp) {
        //将结果保存到数据库
        SQLiteDatabase db = dBopenhelper.getWritableDatabase();
        ContentValues values = GlobalContentValues.getInstance(temp);
        values.put(key, result);
    }

    @Override
    public void saveCommit(DBopenhelper dBopenhelper,ContentValues values,int temp){
        SQLiteDatabase db = dBopenhelper.getWritableDatabase();
        if(temp==0){
            db.insert("test_info", null, values);
        }else{
            db.insert("version_info", null, values);
        }
    }
}
