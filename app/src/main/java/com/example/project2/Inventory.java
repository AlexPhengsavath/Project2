package com.example.project2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.project2.DB.AppDataBase;
import java.util.Date;

@Entity(tableName = AppDataBase.INV_TABLE)
public class Inventory {
    @PrimaryKey(autoGenerate = true)
    private int mInventoryID;

    private String mName;
    private double mValue;
    private int mInventory;

    private Date mDate;

    private int mUserID;

    public Inventory(String name, double value, int inventory, int userID) {
        mName = name;
        mValue = value;
        mInventory = inventory;

        mDate = new Date();

        mUserID = userID;
    }

    public int getInventoryID() {
        return mInventoryID;
    }

    public void setInventoryID(int inventoryID) {
        mInventoryID = inventoryID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getValue() {
        return mValue;
    }

    public void setValue(double value) {
        mValue = value;
    }

    public int getInventory() {
        return mInventory;
    }

    public void setInventory(int inventory) {
        mInventory = inventory;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getUserID() {
        return mUserID;
    }

    public void setUserID(int userID) {
        mUserID = userID;
    }

    @Override
    public String toString() {
        return "Inventory #: " + mInventoryID + "\n" +
                "Name: " + mName + "\n" +
                "Value: $" + mValue + "\n" +
                "=-=-=-=-=-=-\n";
    }

}
