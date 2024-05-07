package com.luizafmartinez.m22_sqliteandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizafmartinez.m22_sqliteandroid.database.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val dbHelper = DatabaseHelper(this)

        try {
            bancoDados.writableDatabase.execSQL(
                "INSERT INTO produtos VALUES(null, 'Notebook Acer', 'Descricao...');"
            )
        } catch (e: Exception) {

        }
    }
}