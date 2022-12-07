package com.example.bmi_calculator

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DialogueBinding = findViewById<Button>(R.id.about)

        DialogueBinding.setOnClickListener{
            val info = layoutInflater.inflate(R.layout.alert_box,null)

            val box = Dialog(this)
            box.setCancelable(true)
            box.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            box.show()

            val ok = info.findViewById<Button>(R.id.leave)
            ok.setOnClickListener{
                box.cancel()
            }
        }

        find.setOnClickListener{

               val weight = W.text.toString()
               val height = H.text.toString()

               if(userinput(weight,height)) {

                  val r = weight.toFloat()/((height.toFloat()/100)*height.toFloat()/100)
                  val bmi = String.format("%.2f", r).toFloat()
                  result(bmi)
                    }
               }
        }
        private fun userinput(weight:String?,height:String?):Boolean{
            return when{
                weight.isNullOrEmpty() -> {
                    Toast.makeText(this,"Weight field is required",Toast.LENGTH_LONG).show()
                    return false
                }
               height.isNullOrEmpty() -> {
                   Toast.makeText(this,"Height field is required",Toast.LENGTH_LONG).show()
                   return false
               }
               else -> {
                   return true
               }
            }
        }

        private fun result(r:Float){
            val final_result = findViewById<TextView>(R.id.calc_result)
            val st_mnt = findViewById<TextView>(R.id.statement)

            var disp_text = ""
            var c = 0
            final_result.text = r.toString()

            when{
                r<18.50->{
                    disp_text = "UNDERWEIGHT"
                    c = R.color.un_wght

                }
                r in 18.50..24.99->{
                    disp_text = "HEALTHY"
                    c = R.color.normal
                }
                r in 25.00..29.99->{
                    disp_text = "OVERWEIGHT"
                    c = R.color.ov_wght
                }
                r in 30.00..34.99->{
                    disp_text = "Class I Obesity"
                    c = R.color.coloraccent
                }
                r in 35.00..39.99->{
                    disp_text = "Class II Obesity"
                    c = R.color.coloraccent
                }
                r<40.00->{
                    disp_text = "Class III Obesity"
                    c = R.color.coloraccent

                }
            }
            st_mnt.setTextColor(ContextCompat.getColor(this,c))
            st_mnt.text = disp_text
        }
}

