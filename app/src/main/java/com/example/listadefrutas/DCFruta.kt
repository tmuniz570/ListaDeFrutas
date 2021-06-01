package com.example.listadefrutas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DCFruta(var nome: String?, var desc: String?) : Parcelable