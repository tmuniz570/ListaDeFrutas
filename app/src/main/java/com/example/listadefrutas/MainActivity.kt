package com.example.listadefrutas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadefrutas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterFruta.OnClickListener {

    private var listaFrutas : MutableList<DCFruta> = ArrayList()

    private val adapter by lazy { AdapterFruta(listaFrutas, this) }

    private lateinit var binding: ActivityMainBinding

    companion object {

        const val CODE_RESULT_1 = 1
        const val CODE_RESULT_2 = 2
        const val NEW = "new"
        const val LIST = "list"
        const val SELEC_FRUTA = "selec_fruta"
        const val SELEC_FRUTA_RETURN = "selec_fruta_return"

        const val MAIN_ACTIVITY_REQUEST_CODE_EDIT = 2
        const val MAIN_ACTIVITY_FRUTA_EXTRA = "note_extra"
        const val MAIN_ACTIVITY_FRUTA_POSITION_EXTRA = "note_position_extra"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val vw = binding.root
        setContentView(vw)

        if (savedInstanceState != null) {
            val now = savedInstanceState.getParcelableArrayList<DCFruta>(LIST)
            listaFrutas = now as ArrayList<DCFruta>
        } else {
            listaFrutas.add(DCFruta("Melancia","Melancia é muito bom!"))
            listaFrutas.add(DCFruta("Abacaxi", "Abacaxi é top!"))
            listaFrutas.add(DCFruta("Maça", "Maça é top!"))
            listaFrutas.add(DCFruta("Laranja", "Laranja é top!"))
            listaFrutas.add(DCFruta("Melão", "Melão é top!"))

            adapter.get(listaFrutas)
        }

        setup()

        adapter.get(listaFrutas)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (CODE_RESULT_1 == requestCode) {
                listaFrutas.add(data?.getParcelableExtra(NEW)!!)
                adapter.notifyDataSetChanged()
            } else if (CODE_RESULT_2 == requestCode) {
                deleteFruit(data?.getParcelableExtra(SELEC_FRUTA_RETURN)!!)
            }

        }
    }

    private fun setup() {
        binding.viewfruta.layoutManager = LinearLayoutManager(applicationContext)
        binding.viewfruta.setHasFixedSize(true)
        binding.viewfruta.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddFrutaActivity::class.java)
            startActivityForResult(intent, CODE_RESULT_1)
        }

        val itemTouchHelper = ItemTouchHelper(SwipeDrag(adapter))
        itemTouchHelper.attachToRecyclerView(binding.viewfruta)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        outState.putParcelableArrayList(LIST, listaFrutas)
    }

    override fun onItemClick(item: DCFruta, position: Int) {
        callFruitDetail(item)
    }

    private fun callFruitDetail(item: DCFruta) {
        val intent = Intent(this, InfoActivity::class.java)
        intent.putExtra(SELEC_FRUTA, item)
        startActivityForResult(intent, CODE_RESULT_2)
    }

    private fun deleteFruit(fruta: DCFruta) {
        listaFrutas.remove(fruta)
        adapter.notifyDataSetChanged()
    }

    private fun onFrutaClickListener(fruta: DCFruta, position: Int) {
        val intent = Intent(this, AddFrutaActivity::class.java)
        intent.putExtra(MAIN_ACTIVITY_FRUTA_EXTRA, fruta)
        intent.putExtra(MAIN_ACTIVITY_FRUTA_POSITION_EXTRA, position)
        startActivityForResult(intent, MAIN_ACTIVITY_REQUEST_CODE_EDIT)
    }

}