package com.example.listadefrutas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listadefrutas.databinding.ActivityNewBinding

class AddFrutaActivity : AppCompatActivity() {
    private lateinit var fruta: DCFruta
    private lateinit var binding: ActivityNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fruta = DCFruta(null, null)
        setup()


    }

    private fun setup() {

        binding.add.setOnClickListener {
            val nome = binding.nome.text.toString()
            val desc = binding.desc.text.toString()

            fruta.nome = nome
            fruta.desc = desc

            val retorna = Intent()
            retorna.putExtra(MainActivity.NEW, fruta)
            setResult(RESULT_OK, retorna)
            finish()
        }

        binding.ivFrutaImageAdd.setOnClickListener {
            Toast.makeText(this, "TESTANDO IV", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_add -> {
                val nome = binding.nome.text.toString()
                val desc = binding.desc.text.toString()

                fruta.nome = nome
                fruta.desc = desc

                val retorna = Intent()
                retorna.putExtra(MainActivity.NEW, fruta)
                setResult(RESULT_OK, retorna)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}