package com.hudq.postlogger.intern;

import android.content.Context;
import android.os.Build;

/**
 * 系统设备信息，非必须
 * <p>
 * Created by hudq on 2016/11/24.
 */
public class _OSParams {

    private String imei;
    private String network_type;
    private String wifi_mac;
    private String op_version;
    private String equip_serial;
    private String equip_host;
    private String hardware_manufacturer;
    private String hardware_info;
    private String equip_model;
    private String device_info;
    private String product_manufacturer;
    private String board_info;
    private String cpu_abi;
    private String cpu_abi2;

    //private String device_token;

    public _OSParams(Context context) {
        if (context == null)
            throw new IllegalArgumentException("context required init.");
        context = context.getApplicationContext();

        this.imei = LogerHelp.getIMEI(context);
        this.network_type = LogerHelp.getNetStateType(context);
        this.wifi_mac = LogerHelp.getMacAddress(context);
        this.op_version = Build.VERSION.RELEASE;
        this.equip_serial = Build.SERIAL;
        this.equip_host = Build.HOST;
        this.hardware_manufacturer = Build.PRODUCT;
        this.hardware_info = Build.HARDWARE;
        this.equip_model = Build.MODEL;
        this.device_info = Build.DEVICE;
        this.product_manufacturer = Build.MANUFACTURER;
        this.board_info = Build.BOARD;
        this.cpu_abi = Build.CPU_ABI;
        this.cpu_abi2 = Build.CPU_ABI2;
    }

}
