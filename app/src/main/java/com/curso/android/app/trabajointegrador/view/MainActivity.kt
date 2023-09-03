package com.curso.android.app.trabajointegrador.view

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.activity.viewModels
import com.curso.android.app.trabajointegrador.R
import com.curso.android.app.trabajointegrador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.comparator.observe(this){
            binding.result.text = "RESULTADO: ${it.resultado}"
        }

        binding.compareButton.setOnClickListener {
            val textoUno = binding.primerString.text.toString()
            val textoDos = binding.segundoString.text.toString()
            mainViewModel.compareTexts(textoUno, textoDos)
        }
    }
}