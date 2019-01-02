package com.sandlife.baselibrary.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 获取设备信息
 * Created by Administrator on 2016/9/1.
 */
public class DeviceUtil {
    /**
     * 获取设备的ID信息
     * @param context
     * @return 设备ID  默认为0000000000（10个0）
     */
    public static String getDeviceID(Context context){
        String deviceID = "0000000000";
        try{
            //获取设备ID
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager)context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            deviceID = tm.getDeviceId();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  deviceID;
    }

    public static String[] getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2="";
        String[] cpuInfo={"",""};
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return cpuInfo;
    }
}
