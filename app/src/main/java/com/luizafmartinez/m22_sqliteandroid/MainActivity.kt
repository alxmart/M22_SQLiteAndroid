package com.luizafmartinez.m22_sqliteandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.luizafmartinez.m22_sqliteandroid.database.DatabaseHelper
import com.luizafmartinez.m22_sqliteandroid.database.ProdutoDAO
import com.luizafmartinez.m22_sqliteandroid.databinding.ActivityMainBinding
import com.luizafmartinez.m22_sqliteandroid.model.Produto

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

    //=======================
    //  CREATE
    //=======================
    private fun salvar() {

        val titulo = binding.editProduto.text.toString()
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(
            -1, titulo, "descrição..."
        )
        if (produtoDAO.salvar(produto)) {
            Toast.makeText(
                this,
                "Sucesso ao cadastrar produto",
                Toast.LENGTH_SHORT
            ).show()
            binding.editProduto.setText("")
            //binding.editProduto.text.clear()
        } else {
            Toast.makeText(
                this,
                "Erro ao cadastrar produto",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /*
    val titulo = binding.editProduto.text.toString()
    val sql = "INSERT INTO produtos VALUES(null, '$titulo', 'Descricao...');"
    try {
        bancoDados.writableDatabase.execSQL(sql)
        Log.i("info_db", "Sucesso ao inserir")
    } catch (e: Exception) {
        Log.i("info_db", "Erro ao inserir")
    }

    */

    //=======================
    //  READ
    //=======================
    private fun listar() {

        val produtoDAO = ProdutoDAO(this)
        val listaProdutos = produtoDAO.listar()

        var texto = ""

        if (listaProdutos.isNotEmpty()) {
            listaProdutos.forEach { produto ->
                texto += "${produto.idProduto}, titulo: ${produto.titulo} \n"
                Log.i("info_db","id: ${produto.idProduto}, titulo: ${produto.titulo}")
            }
            binding.textResultado.text = texto
        } else {
            binding.textResultado.text = "Nenhum item cadastrado"
        }
    }

    /*
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
    */

    //=======================
    //  UPDATE
    //=======================
    private fun atualizar() {

        val titulo = binding.editProduto.text.toString()
        val produtoDAO = ProdutoDAO(this)
        //Usa -1 quando não quer definir um valor
        val produto = Produto(
            1, titulo, "descrição..."
        )
        produtoDAO.atualizar(produto)
    }

    //val sql = "UPDATE produtos SET titulo = '$titulo' WHERE id_produto = 1;"//definido manualmente
    /* val sql = "UPDATE ${DatabaseHelper.TABELA_PRODUTOS} " +
               "SET    ${DatabaseHelper.TITULO} = '$titulo', " +
               "WHERE  ${DatabaseHelper.ID_PRODUTO} = 1;" // definido manualmente
     try {
         bancoDados.writableDatabase.execSQL(sql)
         Log.i("info_db", "Sucesso ao atualizar")
     } catch (e: Exception) {
         Log.i("info_db", "Erro ao atualizar")
     }*/

    //=======================
    //  DELETE
    //=======================
    private fun remover() {

        val produtoDAO = ProdutoDAO(this)
        produtoDAO.remover(3)
    }
    /*
     val sql = "DELETE FROM ${DatabaseHelper.TABELA_PRODUTOS} " +
               "WHERE  ${DatabaseHelper.ID_PRODUTO} = 1;" // definido manualmente
     try {
         bancoDados.writableDatabase.execSQL(sql)
         Log.i("info_db", "Sucesso ao remover")
     } catch (e: Exception) {
         Log.i("info_db", "Erro ao remover")
     }
     */
}