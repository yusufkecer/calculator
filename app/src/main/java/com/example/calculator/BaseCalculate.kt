package com.example.calculator

interface BaseCalculate {
    fun equal(veriables: String): Double?
    fun checkOperant(value: Char): Boolean {
        return value in listOf('+', '-', '*', '/')
    }


    fun checkFirstOperator(firstChar: String, input: android.widget.EditText)
    fun checkLastOperator(firstChar: String, input: android.widget.EditText)
}