package com.example.infologger

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)


        val folder: ImageView = findViewById(R.id.folder)

        val user: ImageView =  findViewById(R.id.user)
        val info: ImageView =  findViewById(R.id.info)
        val number: ImageView =  findViewById(R.id.number)

        fun gotoFolder(image: ImageView,delay: Long){
            //waits for image to load then runs
            image.post {

                //gets center point of folder
                val folderX = folder.x + folder.width / 2
                val folderY = folder.y + folder.height / 2

                //gets center point of each image
                val imageX = image.x + image.width / 2
                val imageY = image.y + image.height / 2

                //gets distance of image to folder
                val moveX = folderX - imageX
                val moveY = folderY - imageY

                image.animate()
                    .translationXBy(moveX)
                    .translationYBy(moveY)
                    .scaleX(0.4f)
                    .scaleY(0.4f)
                    .alpha(0f)
                    .setStartDelay(delay)
                    .setDuration(1200)
                    .start()
            }
        }

        gotoFolder(user, 0)
        gotoFolder(info, 300)
        gotoFolder(number, 600)
    }
}