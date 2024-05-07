package com.luizafmartinez.m22_sqliteandroid.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(contex: Context): SQLiteOpenHelper(
    // 1) Contexto,        2) Nome do BD,
    // 3) CursosrFactory,  4) Versão do BD
    contex, "loja.db", null, 2
) {
    override fun onCreate(db: SQLiteDatabase?) {

        Log.i("info_db", "Executou onCreate")

        val sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                " id_produto integer NOT null PRIMARY KEY AUTOINCREMENT," +
                " titulo varchar(100)," +
                " descricao text" +
                ");"

        try {
            db?.execSQL(sql)
            Log.i("info_db", "Tabela criada com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar a tabela")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        Log.i("info_db", "Executou onUpgrade")

    }
}