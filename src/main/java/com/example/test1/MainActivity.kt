package com.example.test1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bmiValue.visibility = View.INVISIBLE
        bmiOutput.visibility = View.INVISIBLE
        var height: Float
        var weight: Float
        var bmi: Float
        heightInput.setOnClickListener{
            height = heightInput.text.toString().toFloat()
        }
        weightInput.setOnClickListener{
            weight = weightInput.text.toString().toFloat()
        }
        calculateButton.setOnClickListener{

            if (heightInput.text.isEmpty() && weightInput.text.isEmpty()){
                Toast.makeText(this,"please enter value",Toast.LENGTH_SHORT).show()
            }
            else{
                height = heightInput.text.toString().toFloat()
                weight = weightInput.text.toString().toFloat()
                bmi = if (heightInput.hint == "height(cm)"){
                    metricCalculation(weight,height)
                }
                else{
                    imperialCalculation(weight,height)
                }
                Toast.makeText(this,"BMI = $bmi",Toast.LENGTH_SHORT).show()
                showOutput(bmiValue,bmi,bmiOutput)

            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.setting -> {
                Toast.makeText(this,"clicked settings",Toast.LENGTH_SHORT).show()

                true
            }
            R.id.metric -> {
                metricClicked()
                true
            }
            R.id.imperial -> {
                imperialClicked()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }
    private fun metricClicked(){
        heightInput.setHint(R.string.heightInputMetric)
        weightInput.setHint(R.string.weightInputMetric)
        heightInput.text.clear()
        weightInput.text.clear()
    }
    private fun imperialClicked(){
        heightInput.setHint(R.string.heightInputImperial)
        weightInput.setHint(R.string.weightInputImperial)
        heightInput.text.clear()
        weightInput.text.clear()
    }
    private fun metricCalculation(x: Float, y: Float): Float {
        return (x / (y * y)) * 10000
    }
    private fun imperialCalculation(x: Float, y: Float): Float {
        return (x / (y * y)) * 703
    }
    private fun showOutput(x:ImageView,y: Float,z:TextView){
        val underweightMessage  = arrayListOf("eat some food mate","bro you are skinny","you are underweight")
        val normalMessage = arrayListOf("you are normal","maintain your diet you're normal","nothing to say you're normal")
        val overweightMessage = arrayListOf("you are a bit chubby","cut some carbs mate,you are overweight","you are overweight")
        val obeseMessage = arrayListOf("you are obese","must do some drastic diet changes","take care of your body mate")
        val veryObeseMessage = arrayListOf("damn boy you thickkk","do some exercise or die","you are fat as f*ck")
        x.visibility = View.VISIBLE
        z.visibility = View.VISIBLE
        when {
            y<18.5 -> {
                x.setImageResource(R.drawable.underweightbmi)
                underweightMessage.shuffle()
                z.text = underweightMessage[0]
            }
            y in 18.5 .. 24.9 -> {
                x.setImageResource(R.drawable.normalbmi)
                normalMessage.shuffle()
                z.text = normalMessage[0]
            }
            y in 25.0 .. 29.9 -> {
                x.setImageResource(R.drawable.overweightbmi)
                overweightMessage.shuffle()
                z.text = overweightMessage[0]
            }
            y in 30.0 .. 34.9 -> {
                x.setImageResource(R.drawable.obesebmi)
                obeseMessage.shuffle()
                z.text = obeseMessage[0]
            }
            y > 35 -> {
                x.setImageResource(R.drawable.veryobesebmi)
                veryObeseMessage.shuffle()
                z.text = veryObeseMessage[0]
            }
        }
    }

}