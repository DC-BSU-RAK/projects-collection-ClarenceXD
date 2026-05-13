package com.example.tadcships

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay

class intro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3100)

        //text + non-moveable heart
        var stay: ImageView = findViewById(R.id.heart)
        var creator: TextView = findViewById(R.id.creator)

        //hearts images
        var m1: ImageView = findViewById(R.id.a1)
        var m2: ImageView = findViewById(R.id.a2)
        var m3: ImageView = findViewById(R.id.a3)
        var m4: ImageView = findViewById(R.id.a4)
        //new
        var m5: ImageView = findViewById(R.id.a5)
        var m6: ImageView = findViewById(R.id.a6)
        var m7: ImageView = findViewById(R.id.a7)
        var m8: ImageView = findViewById(R.id.a8)
        val shakeAnimation = AnimationUtils.loadAnimation(this,R.anim.shake)

        fun explodeHearts() {

            // center
            stay.startAnimation(shakeAnimation)
            //creator
            creator.animate()
                .alpha(1f)
                .setDuration(3000)
                .start()

            // left
            m1.animate()
                .translationX(-400f)
                .translationY(-250f)
                .alpha(0f)
                .setDuration(3000)
                .start()

            m2.animate()
                .translationX(-350f)
                .translationY(250f)
                .alpha(0f)
                .setDuration(3000)
                .start()

            // left outer
            m5.animate()
                .translationX(-550f)
                .translationY(-550f)
                .alpha(0f)
                .scaleX(2f)
                .scaleY(2f)
                .setDuration(3000)
                .start()

            m6.animate()
                .translationX(-550f)
                .translationY(550f)
                .alpha(0f)
                .scaleX(2f)
                .scaleY(2f)
                .setDuration(3000)
                .start()

            // right
            m3.animate()
                .translationX(350f)
                .translationY(-250f)
                .alpha(0f)
                .setDuration(3000)
                .start()

            m4.animate()
                .translationX(350f)
                .translationY(250f)
                .alpha(0f)
                .setDuration(3000)
                .start()

            // right outer
            m7.animate()
                .translationX(550f)
                .translationY(550f)
                .alpha(0f)
                .scaleX(2f)
                .scaleY(2f)
                .setDuration(3000)
                .start()

            m8.animate()
                .translationX(550f)
                .translationY(-550f)
                .alpha(0f)
                .scaleX(2f)
                .scaleY(2f)
                .setDuration(3000)
                .start()
        }

        explodeHearts()




    }

}