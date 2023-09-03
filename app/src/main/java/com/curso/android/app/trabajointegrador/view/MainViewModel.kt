package com.curso.android.app.trabajointegrador.view
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.android.app.trabajointegrador.model.Comparator
import kotlinx.coroutines.launch
import android.util.Log


class MainViewModel : ViewModel() {
    val comparator: LiveData<Comparator> get() = _comparator
    private var _comparator = MutableLiveData<Comparator>(Comparator(""))

    fun compareTexts(textoUno: String, textoDos: String): Int{
        if(textoUno == textoDos){
            ShowResult(1)
            return 1
        }else{
            ShowResult(0)
            return 0
        }
    }

    private fun ShowResult(valor: Int){
        viewModelScope.launch {
            if(valor == 1){
                _comparator.value = Comparator("Los textos son iguales")
            }else{
                _comparator.value = Comparator("Los textos son diferentes")
            }
        }
    }
}