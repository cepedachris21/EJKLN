package uta.edu.ec.android_app_002.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MasterPersistence extends SQLiteOpenHelper {
    public MasterPersistence(@Nullable Context context, @Nullable String name,
                             @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_CONTACT = "CREATE TABLE Contacts(Code integer PRIMARY KEY AUTOINCREMENT," +
                                      "Name text, LastName text, Age integer)";
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String CREATE_TABLE_CONTACT_UPGRADE = "CREATE TABLE Contacts (Code integer PRIMARY KEY AUTOINCREMENT," +
                                              "Name text, LastName text, Age integer)";
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACT_UPGRADE);
    }
}
