package com.example.project2.DB;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

public class DataTypeConverter {

    @TypeConverter
    public long convertDateToLong(Date date){
        return date.getTime();
    }
    @TypeConverter
    public Date convertLongtoDate(long epoch){
        return new Date(epoch);
    }
}
