package com.example.project2.DB;

import androidx.room.TypeConverters;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.project2.Inventory;
import com.example.project2.User;

@Database(entities = {Inventory.class, User.class}, version = 1)
@TypeConverters(DataTypeConverter.class)
public abstract class AppDataBase extends RoomDatabase{
    public static final String DB_NAME = "Inventory.DB";
    public static final String INV_TABLE = "Inventory_Table";
    public static final String USER_TABLE = "User_Table";

    public abstract InventoryDAO getInventoryDAO();

}
