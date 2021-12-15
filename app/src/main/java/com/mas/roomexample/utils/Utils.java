package com.mas.roomexample.utils;

import android.util.Log;

import androidx.room.TypeConverter;

import java.util.Date;

public class Utils {
    /**
     * this method use to convert date type to can use it on Room database
     * we add the annotation @TypeConverter to declare its
     * **/
    @TypeConverter
    public static long fromDate(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date toDate(long milliseconds) {
        return new Date(milliseconds);
    }
}
