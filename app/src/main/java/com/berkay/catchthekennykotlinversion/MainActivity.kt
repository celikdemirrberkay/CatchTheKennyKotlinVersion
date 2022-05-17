package com.berkay.catchthekennykotlinversion

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var num = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)

        hideImages()



//---------------------------------Count Down Timer-------------------------------------------------
        object : CountDownTimer(20000,1000){

            override fun onTick(p0: Long) {
                time.text = "Kalan Süre : ${p0/1000}"
            }

            override fun onFinish() {
                time.text = "Kalan süre : 0"
//---------------------------------Stopping Kenny onFinish------------------------------------------
                handler.removeCallbacks(runnable)
                for (images in imageArray){
                    images.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over ! ")
                alert.setMessage("Do you wanna play again ? ")
                //Yes Button
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener{dialog, i ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                })
                // No Button
                alert.setNegativeButton("No",DialogInterface.OnClickListener{dialog, i ->
                Toast.makeText(this@MainActivity,"Game Over :(",Toast.LENGTH_SHORT).show() // Toast
                })
                alert.show()
            }
        }.start()
//---------------------------------Count Down Timer Finish------------------------------------------
    }

//---------------------------------Hiding All Images------------------------------------------------
    fun hideImages(){

    runnable = object : Runnable{
        override fun run() {
            for(i in imageArray){
                i.visibility = View.INVISIBLE
            }
            val rand = Random()
            val randomIndex = rand.nextInt(9)
            imageArray[randomIndex].visibility = View.VISIBLE
            handler.postDelayed(runnable,300)
        }

    }
    handler.post(runnable)

    }
//---------------------------------Increase Score---------------------------------------------------
    fun increaseScore(view : View){
        num ++
        score.text = "Skor : ${num}"
    }


}