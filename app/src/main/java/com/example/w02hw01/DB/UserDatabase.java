package com.example.w02hw01.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = User.class, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static final String USER_TABLE = "com.example.w02hw01.DB_USER_TABLE";

    public abstract UserDAO getUserDao();
}
