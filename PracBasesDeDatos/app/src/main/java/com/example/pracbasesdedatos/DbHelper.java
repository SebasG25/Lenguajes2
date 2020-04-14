package com.example.pracbasesdedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    String Tabla = "Create Table Habitaciones (Id Integer primary key autoincrement, Tipo Text, Precio Double, Direccion Text, Nombre Text, Telefono Text, Fecha Text, Arrendada Text)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table Habitaciones");
        db.execSQL(Tabla);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
}
