package com.example.calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var input: String = ""

    private val calculate = Calculate()
    private var textEdit: android.widget.EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        textEdit = binding.input
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                input = s.toString()
                textEdit!!.setSelection(textEdit!!.text.length)
            }
        })
    }

    fun add(view: View) {
        setValue('+')
    }

    fun sub(view: View) {
        setValue('-')
    }

    fun multi(view: View) {
        setValue('x')
    }

    fun dvde(view: View) {
        setValue('÷')
    }

    fun equal(view: View) {
        val value = calculate.equal(input)
        if (value != null && value.toString() != "Infinity") {
            binding.result.text = value.toString()
        } else {
            binding.result.text = "Hata"
        }
    }

    private fun setValue(setValue: Char) {
        calculate.checkLastOperator(input, textEdit!!)
        val fullValue = input + setValue
        textEdit!!.setText(fullValue)
        calculate.checkFirstOperator(input, textEdit!!)
    }
}
