package com.fibocom.engineeringMode.bean;

import android.content.ContentValues;

public class GlobalContentValues {
    private static ContentValues instance_test = null;
    private static ContentValues instance_version = null;


    //0表示test_info表，1表示version_info表
    public static synchronized ContentValues getInstance(int temp) {
        if(temp==0){
            if(instance_test==null){
                instance_test = new ContentValues();
            }
            return instance_test;
        }else{
            if (instance_version == null) {
                instance_version = new ContentValues();
            }
            return instance_version;
        }
    }
}
