package com.fibocom.engineeringMode.db;

import android.content.ContentValues;

public interface DbUtil {
    void save(String result,String key,DBopenhelper dBopenhelper,int temp);
    void saveCommit(DBopenhelper dBopenhelper, ContentValues values, int temp);
}
