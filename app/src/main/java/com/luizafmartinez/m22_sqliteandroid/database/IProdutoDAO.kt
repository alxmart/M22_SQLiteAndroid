package com.luizafmartinez.m22_sqliteandroid.database

import com.luizafmartinez.m22_sqliteandroid.model.Produto

interface IProdutoDAO {

    // Create
    fun salvar(produto: Produto): Boolean

    // Read
    fun listar(): List<Produto>

    // Update
    fun atualizar(produto: Produto): Boolean

    // Delete
    fun remover(idProduto: Int): Boolean

}