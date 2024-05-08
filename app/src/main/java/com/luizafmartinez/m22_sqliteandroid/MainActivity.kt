package com.luizafmartinez.m22_sqliteandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

            btnAtualizar.setOnClickListener {
                atualizar()
            }

            btnRemover.setOnClickListener {
                remover()
            }
        }
    }

    /*
    override fun onResume() {
        super.onResume()
        salvar()
    }
    */

    private fun salvar() {

        val titulo = binding.editProduto.text.toString()

        val sql = "INSERT INTO produtos VALUES(null, '$titulo', 'Descricao...');"

        try {
            bancoDados.writableDatabase.execSQL(sql)
            Log.i("info_db", "Sucesso ao inserir")
        } catch (e: Exception) {
            Log.i("info_db", "Erro ao inserir")
        }
    }

    private fun listar() {

        val sql = "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS};"

        val cursor = bancoDados.readableDatabase.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex("${DatabaseHelper.ID_PRODUTO}")
        val indiceTitulo = cursor.getColumnIndex("${DatabaseHelper.TITULO}")
        val indiceDescricao = cursor.getColumnIndex("${DatabaseHelper.DESCRICAO}")

        while (cursor.moveToNext()) {  // false ou true
            val idProduto = cursor.getInt(indiceId)
            val titulo = cursor.getString(indiceTitulo)
            val descricao = cursor.getString(indiceDescricao)
            Log.i(
                "info_db",
                "id: $idProduto, titulo: $titulo, descricao: $descricao"
            )
        }
    }

    private fun atualizar() {

        val titulo = binding.editProduto.text.toString()
        //val sql = "UPDATE produtos SET titulo = '$titulo' WHERE id_produto = 1;"//definido manualmente
        val sql = "UPDATE ${DatabaseHelper.TABELA_PRODUTOS} " +
                  "SET    ${DatabaseHelper.TITULO} = '$titulo', " +
                  "WHERE  ${DatabaseHelper.ID_PRODUTO} = 1;" // definido manualmente
        try {
            bancoDados.writableDatabase.execSQL(sql)
            Log.i("info_db", "Sucesso ao atualizar")
        } catch (e: Exception) {
            Log.i("info_db", "Erro ao atualizar")
        }
    }

    private fun remover() {

        val sql = "DELETE FROM ${DatabaseHelper.TABELA_PRODUTOS} " +
                  "WHERE  ${DatabaseHelper.ID_PRODUTO} = 1;" // definido manualmente
        try {
            bancoDados.writableDatabase.execSQL(sql)
            Log.i("info_db", "Sucesso ao remover")
        } catch (e: Exception) {
            Log.i("info_db", "Erro ao remover")
        }

    }

}