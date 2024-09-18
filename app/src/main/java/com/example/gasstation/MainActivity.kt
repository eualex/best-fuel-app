package com.example.gasstation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

const val GASOLINE_BEST_PRICE_PERCENTAGE = 0.7

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcohol: TextInputLayout
    private lateinit var editAlcohol: TextInputEditText

    private lateinit var textInputGasoline: TextInputLayout
    private lateinit var editGasoline: TextInputEditText

    private lateinit var submitButton: Button
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initInterfaceComponents()

        submitButton.setOnClickListener {
            calculateBestPrice()
        }
    }

    private fun calculateBestPrice() {
        val gasolinePrice = editGasoline.text.toString()
        val alcoholPrice = editAlcohol.text.toString()

        val isFieldValid = validateFields(gasolinePrice, alcoholPrice)

        if (!isFieldValid) return

        val result = alcoholPrice.toDouble() / gasolinePrice.toDouble()

        val bestChoice = if (result >= GASOLINE_BEST_PRICE_PERCENTAGE) "a galosina" else "o álcool"

        resultText.text = "Melhor utilizar $bestChoice"

    }

    private fun validateFields(gasolinePrice: String, alcoholPrice: String): Boolean {
        textInputAlcohol.error = null
        textInputGasoline.error = null

        if (alcoholPrice.isEmpty()) {
            textInputAlcohol.error = "Digite o preço do álcool"
            return false
        }

        if (gasolinePrice.isEmpty()) {
            textInputAlcohol.error = "Digite o preço da gasolina"
            return false
        }

        return true
    }

    private fun initInterfaceComponents() {
        textInputAlcohol = findViewById(R.id.text_input_alcohol)
        editAlcohol = findViewById(R.id.edit_acohol)
        textInputGasoline = findViewById(R.id.text_input_gasoline)
        editGasoline = findViewById(R.id.edit_gasoline)
        submitButton = findViewById(R.id.submit_button)
        resultText = findViewById(R.id.result)
    }
}