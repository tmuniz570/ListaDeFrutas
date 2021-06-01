package com.example.listadefrutas

import android.content.Intent
import android.os.Bundle
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
}