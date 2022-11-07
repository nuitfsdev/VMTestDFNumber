package com.example.vmtestdfnumber;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_PRECISE_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.Sensor.TYPE_ORIENTATION;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Debug;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Utils {

    // Default Number
    private static String[] known_numbers = {
            "+15555215554", // default number + VirusTotal
            "+15555215556", "+15555215558", "+15555215560", "+15555215562", "+15555215564", "+15555215566",
            "+15555215568", "+15555215570", "+15555215572", "+15555215574", "+15555215576", "+15555215578",
            "+15555215580", "+15555215582", "+15555215584",};


    public static boolean hasKnownPhoneNumber(TextView textView, AppCompatActivity act, Context context) {
        if((ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(act, new String[]{READ_PHONE_NUMBERS}, 0x12);

            Log.d("test", "No permission");
            return false;
        } else {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String phoneNumber = telephonyManager.getLine1Number();
            Log.d("phoneNumber:", phoneNumber);
            for (String number : known_numbers) {
                if (number.equalsIgnoreCase(phoneNumber)) {
                    Log.d("test", "find : " + number);
                    textView.setText("Find phone number: " + phoneNumber+". This is an emulator.");
                    return true;
                }
            }
            Log.d("test", "No default number!");
            textView.setText("Not find  default phone number! This is a real Android device.");
            return false;
        }
    }


}