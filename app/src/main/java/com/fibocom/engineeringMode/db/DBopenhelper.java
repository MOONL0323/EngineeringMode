package com.fibocom.engineeringMode.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBopenhelper extends SQLiteOpenHelper{

    private  static DBopenhelper instance=null;
    public static final String CREATE_VERSION_INFO = "create table version_info(" +
            "data integer primary key autoincrement, " +
            "board text, " +
            "bootloader text, " +
            "brand text, " +
            "cpu_abi text, " +
            "device text, " +
            "fingerprint text, " +
            "hardware text, " +
            "host text, " +
            "id text, " +
            "model text, " +
            "manufacturer text, " +
            "product text, " +
            "tags text, " +
            "time integer, " +
            "type text, " +
            "build_user text, " +
            "version_release text, " +
            "version_codename text, " +
            "version_incremental text, " +
            "version_sdk integer)";

    public static final String CREATE_TEST_INFO = "create table test_info(" +
            "data integer primary key autoincrement, " +
            "camera1 String, " +
            "camera2 String, " +
            "camera3 String, " +
            "camera4 String, " +
            "camera5 String, " +
            "camera6 String, " +
            "camera7 String, " +
            "camera8 String, " +
            "camera9 String, " +
            "camera10 String, " +
            "camera11 String, " +
            "screenTouch String, " +
            "screen String, " +
            "gnss String, " +
            "gyroscope String, " +
            "temperature String, " +
            "sim String)";
    public DBopenhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建两张表，version_info和test_info
        db.execSQL(CREATE_VERSION_INFO);
        db.execSQL(CREATE_TEST_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static synchronized DBopenhelper getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        if(instance==null){
            instance=new DBopenhelper(context,name,factory,version);
        }
        return instance;
    }
}
