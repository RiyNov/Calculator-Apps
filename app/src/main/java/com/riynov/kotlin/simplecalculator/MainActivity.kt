package com.riynov.kotlin.simplecalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        tvOne.setOnClickListener { AppendOnExpression(string = "1", canClear = true) }
        tvTwo.setOnClickListener { AppendOnExpression(string = "2", canClear = true) }
        tvThree.setOnClickListener { AppendOnExpression(string = "3", canClear = true) }
        tvFour.setOnClickListener { AppendOnExpression(string = "4", canClear = true) }
        tvFive.setOnClickListener { AppendOnExpression(string = "5", canClear = true) }
        tvSix.setOnClickListener { AppendOnExpression(string = "6", canClear = true) }
        tvSeven.setOnClickListener { AppendOnExpression(string = "7", canClear = true) }
        tvEight.setOnClickListener { AppendOnExpression(string = "8", canClear = true) }
        tvNine.setOnClickListener { AppendOnExpression(string = "9", canClear = true) }
        tvZero.setOnClickListener { AppendOnExpression(string = "0", canClear = true) }
        tvDot.setOnClickListener { AppendOnExpression(string = ".", canClear = true) }

        //Operators
        tvPlus.setOnClickListener { AppendOnExpression(string = "+", canClear = false) }
        tvMin.setOnClickListener { AppendOnExpression(string = "-", canClear = false) }
        tvMul.setOnClickListener { AppendOnExpression(string = "*", canClear = false) }
        tvDivide.setOnClickListener { AppendOnExpression(string = "/", canClear = false) }
        tvEquals.setOnClickListener { AppendOnExpression(string = "=", canClear = false) }
        tvOpen.setOnClickListener { AppendOnExpression(string = "(", canClear = false) }
        tvClose.setOnClickListener { AppendOnExpression(string = ")", canClear = false) }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if(string.isNotEmpty()) {
                tvExpression.text = string.substring(0, string.length-1)
            }
            tvResult.text = ""
        }

        tvEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble()) {
                    tvResult.text = longResult.toString()
                } else {
                    tvResult.text = result.toString()
                }
            } catch (e:Exception) {
                Log.d("Exception", "message : " + e.message)
            }
        }

    }

    fun AppendOnExpression( string : String, canClear : Boolean ) {

        if(tvResult.text.isNotEmpty()) {
            tvExpression.text = ""
        }

        if(canClear){
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }

}
