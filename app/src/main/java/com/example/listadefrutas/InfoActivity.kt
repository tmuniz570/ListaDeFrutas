package com.example.listadefrutas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listadefrutas.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private lateinit var fruta: DCFruta
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent
        fruta = bundle.getParcelableExtra(MainActivity.SELEC_FRUTA)!!

        setup()

    }

    private fun setup() {
        binding.nomeSelect.text = fruta.nome
        binding.descSelect.text = fruta.desc

        binding.button.setOnClickListener {
            val retorno = Intent()
            retorno.putExtra(MainActivity.SELEC_FRUTA_RETURN, fruta)
            setResult(Activity.RESULT_OK, retorno)
            finish()
        }

    }

}