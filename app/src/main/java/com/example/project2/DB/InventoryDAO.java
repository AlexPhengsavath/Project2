package com.example.project2.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project2.Inventory;
import com.example.project2.User;

import java.util.List;

public interface InventoryDAO {

    @Insert
    void insert(Inventory... inventories);

    @Update
    void update(Inventory... inventories);

    @Delete
    void delete(Inventory inventory);

    @Query("SELECT * FROM " + AppDataBase.INV_TABLE + " ORDER BY mDate desc")
    List<Inventory> getAllInventory();

    @Query("SELECT * FROM " + AppDataBase.INV_TABLE + " WHERE mInventoryId = :inventoryId ORDER BY mDate desc")
    List<Inventory> getInventoryById(int inventoryId);

    @Query("SELECT * FROM " + AppDataBase.INV_TABLE + " WHERE mUserId = :userId ORDER BY mDate desc")
    List<Inventory> getInventoryByUserId(int userId);

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User user);

    @Query( "SELECT * FROM " + AppDataBase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUsername = :username ")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUserId = :userId ")
    User getUserByUserId(int userId);

}
