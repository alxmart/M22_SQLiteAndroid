package com.luizafmartinez.m22_sqliteandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizafmartinez.m22_sqliteandroid.database.DatabaseHelper
import com.luizafmartinez.m22_sqliteandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //val dbHelper = DatabaseHelper(this)

        with(binding) {

            btnSalvar.setOnClickListener {
                salvar()
            }

            btnListar.setOnClickListener {
                listar()
            }




        }




    }

    private fun listar() {


    }

    override fun onResume() {
        super.onResume()
        salvar()
    }

        private fun salvar() {

            val titulo = binding.editProduto.text.toString()
            val sql = "INSERT INTO produtos VALUES(null, '$titulo', 'Descricao...');"

            try {
                bancoDados.writableDatabase.execSQL( sql )
                Log.i("info_db", "Sucesso ao inserir")
            } catch (e: Exception) {
                Log.i("info_db", "Erro ao inserir")
            }
        }



}








