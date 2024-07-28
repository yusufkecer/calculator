package com.example.calculator

import android.widget.EditText

class Calculate : BaseCalculate {

    override fun equal(veriables: String): Double? {
        try {
            val list = veriables.split("+", "-", "x", "÷")
            val operator = veriables.filter { it in listOf('+', '-', 'x', '÷') }
            var result = 0.0
            for (i in list.indices) {
                if (i == 0) {
                    result = list[i].toDouble()
                } else {
                    when (operator[i - 1]) {
                        '+' -> result += list[i].toDouble()
                        '-' -> result -= list[i].toDouble()
                        'x' -> result *= list[i].toDouble()
                        '÷' -> result /= list[i].toDouble()
                    }
                }
            }
            return result
        } catch (e: Exception) {
            return null
        }
    }

    override fun checkFirstOperator(firstChar: String, input: EditText) {
        if (checkOperant(firstChar[0])) {
            input.setText(input.text.substring(1, input.length()))
        }

    }

    override fun checkLastOperator(firstChar: String, input: EditText) {
        if (input.text.isNotEmpty()) {
            val lastChar = input.text[input.text.length - 1]
            val check = checkOperant(lastChar)
            if (check) {
                input.setText(input.text.substring(0, input.text.length - 1))
            }

        }
    }

}