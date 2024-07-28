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
    private var list = listOf<Any>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


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
                binding.input.setSelection(binding.input.text.length)
            }
        })
    }

    fun add(view: View) {
        checkLastOperator()
        setValue("$input+")
        checkFirstOperator()

    }

    fun sub(view: View) {
        checkLastOperator()
        setValue("$input-")
        checkFirstOperator()
    }

    fun multi(view: View) {
        checkLastOperator()
        setValue("$input*")
        checkFirstOperator()
    }

    fun dvde(view: View) {
        checkLastOperator()
        setValue("$input/")
        checkFirstOperator()
    }

    fun equal(view: View) {

    }

    fun setValue(setValue: String) {
        binding.input.setText(setValue)
    }

    private fun checkInput() {
        list = input.split("+", "-", "*", "/")
        if (list.size > 1) {
            val calculate = Calculate()
            //  calculate.equal(list)
        }
    }

    private fun checkLastOperator() {
        if (input.isNotEmpty()) {
            val lastChar = input[input.length - 1]
            if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') {
                binding.input.setText(input.substring(0, input.length - 1))
            }
        }
    }

    private fun checkFirstOperator() {
        if (input.isNotEmpty()) {
            val firstChar = input[0]
            if (firstChar == '+' || firstChar == '-' || firstChar == '*' || firstChar == '/') {
                binding.input.setText(input.substring(1, input.length))
            }
        }
    }
}
