package com.luizafmartinez.m22_sqliteandroid.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(contex: Context): SQLiteOpenHelper(
    // 1) Contexto,        2) Nome do BD,
    // 3) CursosrFactory,  4) Versão do BD
    contex, "loja.db", null, 1
) {
    override fun onCreate(db: SQLiteDatabase?) {


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}