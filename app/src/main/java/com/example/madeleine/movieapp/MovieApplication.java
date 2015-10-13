package com.example.madeleine.movieapp;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Madeleine on 12.10.2015.
 */
public class MovieApplication extends Application {

    private DaoMaster daoMaster;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initDatabaseAccess();
    }

    private void initDatabaseAccess() {
        SQLiteDatabase db = new DaoMaster.DevOpenHelper(this, "movies-db", null).getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}