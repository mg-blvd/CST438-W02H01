package com.example.w02hw01.DB;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM USER_TABLE WHERE username == :username")
    User getUsersFromUsername(String username);

    @Query("SELECT password FROM USER_TABLE WHERE username == :username")
    String getPasswordFromUsername(String username);

}
