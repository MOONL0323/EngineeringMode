package com.fibocom.engineeringMode.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fibocom.engineeringMode.db.DBopenhelper;
import com.fibocom.engineeringMode.bean.VersionInfo;
import com.fibocom.myapplication.R;

/**
 *android.os.Build.BOARD：获取设备基板名称
 *android.os.Build.BOOTLOADER：获取设备引导程序版本号
 *android.os.Build.BRAND：获取设备品牌
 *android.os.Build.CPU_ABI：获取设备指令集名称（CPU的类型）
 *android.os.Build.DEVICE：获取设备驱动名称
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
public class VersionInfoActivity extends AppCompatActivity {
    TextView tvVersionInfo;
    VersionInfo versionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_info);
        tvVersionInfo = findViewById(R.id.tv_version_info);
        versionInfo = new VersionInfo();
        versionInfo.setBoard(getBoard());
        versionInfo.setBootloader(getBootloader());
        versionInfo.setBrand(getBrand());
        versionInfo.setCpuAbi(getCpuAbi());
        versionInfo.setDevice(getDevice());
        versionInfo.setFingerprint(getFingerprint());
        versionInfo.setHardware(getHardware());
        versionInfo.setHost(getHost());
        versionInfo.setId(getId());
        versionInfo.setModel(getModel());
        versionInfo.setManufacturer(getManufacturer());
        versionInfo.setProduct(getProduct());
        versionInfo.setTags(getTags());
        versionInfo.setTime(String.valueOf(getTime()));
        versionInfo.setType(getType());
        versionInfo.setBuildUser(getBuildUser());
        versionInfo.setVersionRelease(getVersionRelease());
        versionInfo.setVersionCodename(getVersionCodename());
        versionInfo.setVersionIncremental(getVersionIncremental());
        versionInfo.setVersionSdk(String.valueOf(getVersionSdk()));
        versionInfo.setTime(String.valueOf(System.currentTimeMillis()));

        displayVersionInfo(versionInfo);
        save(versionInfo);

        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v -> finish());

    }

    public String getBoard() {
        return Build.BOARD;
    }

    public String getBootloader() {
        return Build.BOOTLOADER;
    }

    public String getBrand() {
        return Build.BRAND;
    }

    public String getCpuAbi() {
        return Build.CPU_ABI;
    }

    public String getDevice() {
        return Build.DEVICE;
    }

    public String getFingerprint() {
        return Build.FINGERPRINT;
    }

    public String getHardware() {
        return Build.HARDWARE;
    }

    public String getHost() {
        return Build.HOST;
    }

    public String getId() {
        return Build.ID;
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getProduct() {
        return Build.PRODUCT;
    }

    public String getTags() {
        return Build.TAGS;
    }

    public long getTime() {
        return Build.TIME;
    }

    public String getType() {
        return Build.TYPE;
    }

    public String getBuildUser() {
        return Build.USER;
    }

    public String getVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    public String getVersionCodename() {
        return Build.VERSION.CODENAME;
    }

    public String getVersionIncremental() {
        return Build.VERSION.INCREMENTAL;
    }

    public int getVersionSdk() {
        return Build.VERSION.SDK_INT;
    }



    private void displayVersionInfo(VersionInfo versionInfo) {

        tvVersionInfo.setText("设备基板名称：" + versionInfo.getBoard() + "\n"
                + "设备引导程序版本号：" + versionInfo.getBootloader() + "\n"
                + "设备品牌：" + versionInfo.getBrand() + "\n"
                + "设备指令集名称：" + versionInfo.getCpuAbi() + "\n"
                + "设备驱动名称：" + versionInfo.getDevice() + "\n"
                + "设备的唯一标识：" + versionInfo.getFingerprint() + "\n"
                + "设备硬件名称：" + versionInfo.getHardware() + "\n"
                + "设备主机地址：" + versionInfo.getHost() + "\n"
                + "设备版本号：" + versionInfo.getId() + "\n"
                + "手机的型号 设备名称：" + versionInfo.getModel() + "\n"
                + "设备制造商：" + versionInfo.getManufacturer() + "\n"
                + "产品的名称：" + versionInfo.getProduct() + "\n"
                + "设备标签：" + versionInfo.getTags() + "\n"
                + "时间：" + versionInfo.getTime() + "\n"
                + "设备版本类型：" + versionInfo.getType() + "\n"
                + "设备用户名：" + versionInfo.getBuildUser() + "\n"
                + "系统版本字符串：" + versionInfo.getVersionRelease() + "\n"
                + "设备当前的系统开发代号：" + versionInfo.getVersionCodename() + "\n"
                + "系统源代码控制值：" + versionInfo.getVersionIncremental() + "\n"
                + "系统的API级别：" + versionInfo.getVersionSdk() + "\n");
    }

    public void save(VersionInfo versionInfo) {
        //TODO 保存到数据库
        DBopenhelper dBopenhelper = new DBopenhelper(this, "test.db", null, 1);
        SQLiteDatabase db = dBopenhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("data", System.currentTimeMillis());
        values.put("board", versionInfo.getBoard());
        values.put("bootloader", versionInfo.getBootloader());
        values.put("brand", versionInfo.getBrand());
        values.put("cpu_abi", versionInfo.getCpuAbi());
        values.put("device", versionInfo.getDevice());
        values.put("fingerprint", versionInfo.getFingerprint());
        values.put("hardware", versionInfo.getHardware());
        values.put("host", versionInfo.getHost());
        values.put("id", versionInfo.getId());
        values.put("model", versionInfo.getModel());
        values.put("manufacturer", versionInfo.getManufacturer());
        values.put("product", versionInfo.getProduct());
        values.put("tags", versionInfo.getTags());
        values.put("time", versionInfo.getTime());
        values.put("type", versionInfo.getType());
        values.put("build_user", versionInfo.getBuildUser());
        values.put("version_release", versionInfo.getVersionRelease());
        values.put("version_codename", versionInfo.getVersionCodename());
        values.put("version_incremental", versionInfo.getVersionIncremental());
        values.put("version_sdk", versionInfo.getVersionSdk());
        db.insert("version_info", null, values);
    }
}