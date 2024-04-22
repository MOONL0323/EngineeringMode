package com.fibocom.engineeringMode.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fibocom.engineeringMode.db.DBopenhelper;
import com.fibocom.engineeringMode.bean.VersionInfo;
import com.fibocom.myapplication.R;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
/**
 *android.os.Build.BOARD：获取设备基板名称
 *android.os.Build.BOOTLOADER：获取设备引导程序版本号
 *android.os.Build.BRAND：获取设备品牌
 *android.os.Build.CPU_ABI：获取设备指令集名称（CPU的类型）
 *android.os.Build.DEVICE：获取设备驱动名称
 *android.os.Build.DISPLAY：获取设备显示的版本包（在系统设置中显示为版本号）和ID一样
 *android.os.Build.FINGERPRINT：设备的唯一标识。由设备的多个信息拼接合成
 *android.os.Build.HARDWARE：设备硬件名称，一般和基板名称一样（BOARD）
 *android.os.Build.HOST：设备主机地址
 *android.os.Build.ID：设备版本号
 *android.os.Build.MODEL：获取手机的型号 设备名称。如：SM-N9100（三星Note4）
 *android.os.Build.MANUFACTURER：获取设备制造商。如：samsung
 *android:os.Build.PRODUCT：产品的名称
 *android.os.Build.TAGS：设备标签。如release-keys或测试的test-keys
 *android.os.Build.TIME：时间
 *android.os.Build.TYPE：设备版本类型主要为”user” 或”eng”
 *android.os.Build.USER：设备用户名 基本上都为android-build
 *android.os.Build.VERSION.RELEASE：获取系统版本字符串
 *android.os.Build.VERSION.CODENAME：设备当前的系统开发代号，一般使用REL代替
 *android.os.Build.VERSION.INCREMENTAL：系统源代码控制值，一个数字或者git哈希值
 *android.os.Build.VERSION.SDK：系统的API级别，推荐使用下面的SDK_INT来查看
 *android.os.Build.VERSION.SDK_INT：系统的API级别，int数值类型
 */

public class MainActivity extends AppCompatActivity {
    long data;
    DBopenhelper dBopenhelper;
    SQLiteDatabase db;
    String board;
    String bootloader;
    String brand;
    String cpuAbi;
    String device;
    String fingerprint;
    String hardware;
    String host;
    String id;
    String model;
    String manufacturer;
    String product;
    String tags;
    String time;
    String type;
    String buildUser;
    String versionRelease;
    String versionCodename;
    String versionIncremental;
    String versionSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonManualStart = findViewById(R.id.button_manual);
        Button buttonBack = findViewById(R.id.button_back);
        Button buttonsave = findViewById(R.id.button_save);

        buttonBack.setOnClickListener(v -> {
            finish();
        });

        buttonManualStart.setOnClickListener(v -> {
            // Start home
            startActivity(new Intent(MainActivity.this, ManualEngineeringModeActivity.class));
        });

        buttonsave.setOnClickListener(v -> {
            dBopenhelper = DBopenhelper.getInstance(this, "test.db", null, 1);
            db = dBopenhelper.getWritableDatabase();
            //从数据库中读取最近一次的版本信息
            Cursor cursor = db.query("version_info", null, null, null, null, null, "data DESC", "1");
            if (cursor.moveToFirst()) {
                do {
                    //遍历Cursor对象，取出数据并打印
                    data = cursor.getLong(cursor.getColumnIndex("data"));
                    board = cursor.getString(cursor.getColumnIndex("board"));
                    bootloader = cursor.getString(cursor.getColumnIndex("bootloader"));
                    brand = cursor.getString(cursor.getColumnIndex("brand"));
                    cpuAbi = cursor.getString(cursor.getColumnIndex("cpu_abi"));
                    device = cursor.getString(cursor.getColumnIndex("device"));
                    fingerprint = cursor.getString(cursor.getColumnIndex("fingerprint"));
                    hardware = cursor.getString(cursor.getColumnIndex("hardware"));
                    host = cursor.getString(cursor.getColumnIndex("host"));
                    id = cursor.getString(cursor.getColumnIndex("id"));
                    model = cursor.getString(cursor.getColumnIndex("model"));
                    manufacturer = cursor.getString(cursor.getColumnIndex("manufacturer"));
                    product = cursor.getString(cursor.getColumnIndex("product"));
                    tags = cursor.getString(cursor.getColumnIndex("tags"));
                    time = cursor.getString(cursor.getColumnIndex("time"));
                    type = cursor.getString(cursor.getColumnIndex("type"));
                    buildUser = cursor.getString(cursor.getColumnIndex("build_user"));
                    versionRelease = cursor.getString(cursor.getColumnIndex("version_release"));
                    versionCodename = cursor.getString(cursor.getColumnIndex("version_codename"));
                    versionIncremental = cursor.getString(cursor.getColumnIndex("version_incremental"));
                    versionSdk = cursor.getString(cursor.getColumnIndex("version_sdk"));
                    VersionInfo versionInfo = new VersionInfo();
                    versionInfo.setData(data);
                    versionInfo.setBoard(board);
                    versionInfo.setBootloader(bootloader);
                    versionInfo.setBrand(brand);
                    versionInfo.setCpuAbi(cpuAbi);
                    versionInfo.setDevice(device);
                    versionInfo.setFingerprint(fingerprint);
                    versionInfo.setHardware(hardware);
                    versionInfo.setHost(host);
                    versionInfo.setId(id);
                    versionInfo.setModel(model);
                    versionInfo.setManufacturer(manufacturer);
                    versionInfo.setProduct(product);
                    versionInfo.setTags(tags);
                    versionInfo.setTime(time);
                    versionInfo.setType(type);
                    versionInfo.setBuildUser(buildUser);
                    versionInfo.setVersionRelease(versionRelease);
                    versionInfo.setVersionCodename(versionCodename);
                    versionInfo.setVersionIncremental(versionIncremental);
                    versionInfo.setVersionSdk(versionSdk);


                } while (cursor.moveToNext());
            }

            //TODO: 创建一个文件，包含版本信息和诊断测试结果
            String filename = System.currentTimeMillis() + ".txt";
            //把最近一次日期的数据库中的版本信息字段写入文件
            try {
                Files.write(
                        new File(getFilesDir(), filename).toPath(),
                                ("测试时间：" + data + "\n"
                                + "设备基板名称：" + board + "\n"
                                + "设备引导程序版本号：" + bootloader + "\n"
                                + "设备品牌：" + brand + "\n"
                                + "设备指令集名称：" + cpuAbi + "\n"
                                + "设备驱动名称：" + device + "\n"
                                + "设备的唯一标识：" + fingerprint + "\n"
                                + "设备硬件名称：" + hardware + "\n"
                                + "设备主机地址：" + host + "\n"
                                + "设备版本号：" + id + "\n"
                                + "手机的型号 设备名称：" + model + "\n"
                                + "设备制造商：" + manufacturer + "\n"
                                + "产品的名称：" + product + "\n"
                                + "设备标签：" + tags + "\n"
                                + "时间：" + time + "\n"
                                + "设备版本类型：" + type + "\n"
                                + "设备用户名：" + buildUser + "\n"
                                + "系统版本字符串：" + versionRelease + "\n"
                                + "设备当前的系统开发代号：" + versionCodename + "\n"
                                + "系统源代码控制值：" + versionIncremental + "\n"
                                + "系统的API级别：" + versionSdk + "\n").getBytes()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Log.d("111",String.valueOf(getFilesDir()));
        });

    }
}
