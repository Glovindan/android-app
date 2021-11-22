package com.example.sreda.ui.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sreda.R
import com.example.sreda.ui.base.BaseActivity

class CalcActivity : BaseActivity<CalcViewModel>(CalcViewModel::class) {
    private var firstNumber = "";
    private var secondNumber = "";
    private var operator = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
    }

    private fun updateEditText() {
        val input = findViewById<EditText>(R.id.input);
        val text = firstNumber + operator + secondNumber
        input.setText(text);
    }

    fun onNumberClick(v: View) {
        val b: Button = v as Button;
        val buttonText = b.text.toString()
        if(operator.isEmpty()) {
            this.firstNumber += buttonText;
        } else {
            this.secondNumber += buttonText;
        }
        this.updateEditText();
    }

    fun onOperatorClick(v: View) {
        val b: Button = v as Button;
        val buttonText = b.text.toString();
        this.operator = buttonText;
        this.updateEditText();
    }

    fun onDeleteClick(v: View) {
        if(secondNumber.isNotEmpty()) {
            secondNumber = secondNumber.dropLast(1);
            updateEditText();
            return
        }
        if(operator.isNotEmpty()) {
            operator = operator.dropLast(1);
            updateEditText();
            return
        }
        if(firstNumber.isNotEmpty()) {
            firstNumber = firstNumber.dropLast(1);
            updateEditText();
            return
        }
    }

    fun onEqualClick(v: View) {
        if(secondNumber.isNotEmpty() && firstNumber.isNotEmpty() && operator.isNotEmpty()) {
            var answer = 0.0;
            val firstNumberDouble = firstNumber.toDouble();
            val secondNumberDouble = secondNumber.toDouble();
            answer = when(operator) {
                "+" -> firstNumberDouble + secondNumberDouble;
                "-" -> firstNumberDouble - secondNumberDouble;
                "÷" -> firstNumberDouble / secondNumberDouble;
                "×" -> firstNumberDouble * secondNumberDouble;
                else -> Double.NaN;
            }

            this.firstNumber = answer.toString();
            this.operator = "";
            this.secondNumber = "";

            this.updateEditText();
        }
    }
}