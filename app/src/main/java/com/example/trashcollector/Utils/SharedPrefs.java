package com.example.trashcollector.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.trashcollector.WastCollectorApp;

import java.util.ArrayList;

public class SharedPrefs {

    SharedPreferences sharedPreferences;
    private Context mContext;
    private static String sharedPrefenceName = "CoalShashtra";

    public static SharedPreferences getSharedPref() {
        return WastCollectorApp.getContext().getSharedPreferences(sharedPrefenceName, Context.MODE_PRIVATE);
    }

    public interface userSharedPrefData {
        String app_name = "";
        String welcometext="";
        String selectlanguage = "";
        String liketodo = "";
        String deposittrash="";
        String usewallet = "";
        String smartphoneuser = "";
        String nonsmartuser = "";
        String entermobile = "";
        String ok = "";
        String entermobilenumber = "";
        String enteradar = "";
        String verifymobile = "";
        String editnumber = "";
        String next = "";
        String trashtype = "";
        String plastic = "";
        String glass="";

        String tincans = "";
        String otherwaste = "";
        String pleasedropthetrash = "";
        String checktrsah = "";
        String sorry = "";
        String othertrash = "";
        String thankyou = "";
        String wallet = "";

        String validate = "";


        String morepplastic = "";
        String cleancountry = "";
        String exit = "";
        String done = "";
        String back = "";
        String langaugekey = "";



    }

}
