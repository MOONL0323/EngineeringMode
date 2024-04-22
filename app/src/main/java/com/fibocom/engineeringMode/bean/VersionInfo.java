package com.fibocom.engineeringMode.bean;

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
public class VersionInfo {
    private long data;
    private String Board;
    private String Bootloader;
    private String Brand;
    private String CpuAbi;
    private String Device;
    private String Fingerprint;
    private String Hardware;
    private String Host;
    private String Id;
    private String Model;
    private String Manufacturer;
    private String Product;
    private String Tags;
    private String Time;
    private String Type;
    private String User;
    private String VersionRelease;
    private String VersionCodename;
    private String VersionIncremental;
    private String VersionSdk;

    public void setBoard(String board) {
        Board = board;
    }

    public void setBootloader(String bootloader) {
        Bootloader = bootloader;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setCpuAbi(String cpuAbi) {
        CpuAbi = cpuAbi;
    }


    public void setDevice(String device) {
        Device = device;
    }


    public void setFingerprint(String fingerprint) {
        Fingerprint = fingerprint;
    }

    public void setHardware(String hardware) {
        Hardware = hardware;
    }

    public void setHost(String host) {
        Host = host;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setModel(String model) {
        Model = model;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setBuildUser(String user) {
        User = user;
    }

    public void setVersionRelease(String versionRelease) {
        VersionRelease = versionRelease;
    }

    public void setVersionCodename(String versionCodename) {
        VersionCodename = versionCodename;
    }

    public void setVersionIncremental(String versionIncremental) {
        VersionIncremental = versionIncremental;
    }

    public void setVersionSdk(String versionSdk) {
        VersionSdk = versionSdk;
    }



    public String getBoard() {
        return Board;
    }

    public String getBootloader() {
        return Bootloader;
    }

    public String getBrand() {
        return Brand;
    }

    public String getCpuAbi() {
        return CpuAbi;
    }

    public String getDevice() {
        return Device;
    }


    public String getFingerprint() {
        return Fingerprint;
    }

    public String getHardware() {
        return Hardware;
    }

    public String getHost() {
        return Host;
    }

    public String getId() {
        return Id;
    }

    public String getModel() {
        return Model;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public String getProduct() {
        return Product;
    }


    public String getTags() {
        return Tags;
    }

    public String getTime() {
        return Time;
    }

    public String getType() {
        return Type;
    }

    public String getBuildUser() {
        return User;
    }

    public String getVersionRelease() {
        return VersionRelease;
    }

    public String getVersionCodename() {
        return VersionCodename;
    }

    public String getVersionIncremental() {
        return VersionIncremental;
    }

    public String getVersionSdk() {
        return VersionSdk;
    }


    public void setData(long data) {
        this.data = data;
    }
}