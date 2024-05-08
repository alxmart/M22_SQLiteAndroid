package com.luizafmartinez.m22_sqliteandroid.database

import android.content.Context
import android.util.Log
import com.luizafmartinez.m22_sqliteandroid.model.Produto

class ProdutoDAO(context: Context) : IProdutoDAO{

    val escrita = DatabaseHelper(context).writableDatabase
    val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(produto: Produto): Boolean {

        val sql = "INSERT INTO produtos VALUES(null, '', 'Descricao...');"

        try {
            escrita.execSQL(sql)
            Log.i("info_db", "Sucesso ao inserir")
        } catch (e: Exception) {
            Log.i("info_db", "Erro ao inserir")
        }

    }

    override fun listar(): List<Produto> {

    }

    override fun atualizar(produto: Produto): Boolean {

    }

    override fun remover(idProduto: Int): Boolean {

    }


}