package com.khdev.testwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var check = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt_start = findViewById<Button>(R.id.start)
        val bt_time = findViewById<Button>(R.id.time_bt)
        val bt_casual = findViewById<Button>(R.id.casual)
        val bt_ready = findViewById<Button>(R.id.ready)
        val text = findViewById<TextView>(R.id.start_text)
        val ll = findViewById<LinearLayout>(R.id.time_ll)

        bt_start.setOnClickListener{
            text.setText("Choose if you want to play casual or fast mode")
            text.setTextSize(45F)
            bt_start.visibility = View.GONE
            bt_casual.visibility = View.VISIBLE
            bt_time.visibility = View.VISIBLE
        }

        bt_time.setOnClickListener{
            bt_time.visibility = View.GONE
            text.visibility = View.GONE
            bt_casual.visibility = View.GONE
            bt_ready.visibility =View.VISIBLE
            ll.visibility = View.VISIBLE
            timer()
        }

        bt_casual.setOnClickListener{
            bt_time.visibility = View.GONE
            text.visibility = View.GONE
            bt_casual.visibility = View.GONE
            bt_ready.visibility =View.VISIBLE
            ll.visibility = View.VISIBLE
        }

        bt_ready.setOnClickListener{
            check = true
            val timer_text = findViewById<TextView>(R.id.timer)
            timer_text.visibility = View.GONE
            game()

        }

    }

    fun timer(){
        val timer = findViewById<TextView>(R.id.timer)
        timer.visibility = View.VISIBLE
        var tim = 11
        object: CountDownTimer(11000, 1000){
            override fun onTick(p0: Long) {
                --tim
                if(tim<10){
                    timer.setText("00:0$tim")
                }else{
                    timer.setText("00:$tim")
                }
            }
            override fun onFinish() {
                timer.visibility = View.GONE
                if(check == false){
                game()
                }
            }
        }.start()
    }

    private fun game() {
        val ll = findViewById<LinearLayout>(R.id.time_ll)
        val ready = findViewById<Button>(R.id.ready)
        ll.visibility = View.GONE
        ready.visibility = View.GONE

        val img = findViewById<ImageView>(R.id.answ_img)
        val answ_text = findViewById<EditText>(R.id.answ_text)
        val ok = findViewById<Button>(R.id.ok)

        img.visibility = View.VISIBLE
        answ_text.visibility = View.VISIBLE
        ok.visibility = View.VISIBLE

        val start = findViewById<Button>(R.id.start)
        val start_text = findViewById<TextView>(R.id.start_text)

        val restart = findViewById<Button>(R.id.restart)

        img.setImageResource(R.drawable.time)
        var count = 0
        var score = 0
        ok.setOnClickListener{
            var answ = answ_text.text.toString()
            answ = answ.toLowerCase()
            if (count==0){
                img.setImageResource(R.drawable.time1)
                if(answ=="moscow"){
                    score++
                }else{
                    score--
                }
            }else if (count==1){
                img.setImageResource(R.drawable.time2)
                if(answ=="париж"){
                    score++
                }else{
                    score--
                }
            }else if (count==2){
                img.setImageResource(R.drawable.time3)
                if(answ=="лондон"){
                    score++
                }else{
                    score--
                }
            }else if (count==3){
                img.setImageResource(R.drawable.time4)
                if(answ=="сидней"){
                    score++
                }else{
                    score--
                }
            }else if (count==4){
                img.setImageResource(R.drawable.time5)
                if(answ=="токио"){
                    score++
                }else{
                    score--
                }
            }else {
                restart.visibility = View.VISIBLE
                start_text.visibility = View.VISIBLE
                img.visibility = View.GONE
                answ_text.visibility = View.GONE
                ok.visibility = View.GONE
                start_text.setText("Score: $score")
                if(answ=="пекин"){
                    score++
                }else{
                    score--
                }
            }

            count++
        }



        restart.setOnClickListener{
            restart.visibility =View.GONE
            start.visibility = View.VISIBLE
            start_text.setText("MATCH THE TIME ZONE WITH THE CITY")
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}