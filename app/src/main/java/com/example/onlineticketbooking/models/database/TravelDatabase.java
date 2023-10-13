package com.example.onlineticketbooking.models.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.onlineticketbooking.models.login.LoginDao;
import com.example.onlineticketbooking.models.login.LoginEntity;
import com.example.onlineticketbooking.models.utilities.DatabaseTypeConverters;

@Database(entities = {LoginEntity.class}, version = 1)
@TypeConverters({DatabaseTypeConverters.class})
public abstract class TravelDatabase extends RoomDatabase {
    public abstract LoginDao LoginDao();
}
