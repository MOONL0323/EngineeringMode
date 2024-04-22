package com.fibocom.engineeringMode.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.fibocom.engineeringMode.db.DBopenhelper;
import com.fibocom.engineeringMode.db.DbUtil;
import com.fibocom.engineeringMode.db.dbimpl.DbUtilImpl;

public class Util {
    private static DbUtil dbUtil = new DbUtilImpl();
    public static void alertDialog(String title, String message, Context context, String key,
                                   DBopenhelper dBopenhelper, int temp, DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("是", (dialog, which) -> {
                    //将结果保存到数据库
                    dbUtil.save("success",key,dBopenhelper,temp);
                    // 调用提供的监听器
                    if (listener != null) {
                        listener.onClick(dialog, which);
                    }
                })
                .setNegativeButton("否", (dialog, which) -> {
                    //将结果保存到数据库
                    dbUtil.save("error",key,dBopenhelper,temp);
                    // 调用提供的监听器
                    if (listener != null) {
                        listener.onClick(dialog, which);
                    }
                })
                .setCancelable(false)
                .show();
    }



}
