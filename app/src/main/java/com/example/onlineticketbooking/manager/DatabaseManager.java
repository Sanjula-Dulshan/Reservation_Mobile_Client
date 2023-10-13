package com.example.onlineticketbooking.manager;

import android.content.Context;

import androidx.room.Room;

import com.example.onlineticketbooking.models.database.TravelDatabase;

public class DatabaseManager {

    private static DatabaseManager singleton;
    private final String databaseName = "Ticket_db";
    private final ContextManager contextManager;
    private final TravelDatabase database;

    public static DatabaseManager getInstance() {
        if (singleton == null)
            singleton = new DatabaseManager();
        return singleton;
    }

    private DatabaseManager() {
        contextManager = ContextManager.getInstance();
        Context applicationContext = contextManager.getApplicationContext();
        if (applicationContext != null) {
            database = Room.databaseBuilder(
                    contextManager.getApplicationContext(),
                    TravelDatabase.class,
                    databaseName).build();
        } else {
            throw new IllegalStateException("Application context is null. Unable to create database.");
        }
    }

    public TravelDatabase db() {
        return database;
    }
}
