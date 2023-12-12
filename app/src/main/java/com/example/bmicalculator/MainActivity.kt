package com.example.bmicalculator

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.layout_background)
        }

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val btnCal = findViewById<Button>(R.id.btnCal)

        btnCal.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput(weight, height)) {
                val bmi = (weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100)))
                displayResult(bmi)
            }
        }

    }

    private fun validateInput(weight:String?,height:String?):Boolean{
        when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"Please enter weight",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"Please enter Height",Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
    private fun displayResult(bmi:Float){

         val tvOutput = findViewById<TextView>(R.id.tvOutput)
         val tvResult = findViewById<TextView>(R.id.tvResult)
         val tvMessage = findViewById<TextView>(R.id.tvMessage)

         var result = ""
         var color = 0

         when{
             bmi < 18.5 -> {
                 result = "Underweight"
                 color  = R.color.under_weight
             }
             bmi in 18.5..24.9 -> {
                 result = "Normal"
                 color = R.color.normal
             }
             bmi in 25.0..29.9 -> {
                 result = "Overweight"
                 color = R.color.over_weight
             }
             bmi >29.9 ->{
                 result = "Obese"
                 color = R.color.obese
             }
         }

         val output = String.format("%.02f",bmi)
         tvOutput.text = output

         tvResult.text = result
         tvResult.setTextColor(ContextCompat.getColor(this,color))

         val message = "(normal range is 18.5-24.9)"
         tvMessage.text = message


    }

}
