package com.example.infologger

import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class remove : AppCompatActivity() {

    private lateinit var navbar: BottomNavigationView
    private lateinit var go_view: Intent
    private lateinit var go_main: Intent
    private lateinit var spinner: Spinner
    private lateinit var clearselected: Button
    private lateinit var clearall: Button

    private lateinit var storage: SharedPreferences
    private var slotOrder = 0

//    warning audio
    private lateinit var beepAudio: MediaPlayer



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_remove)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        storage = getSharedPreferences("userdata", MODE_PRIVATE)
        clearselected = findViewById(R.id.clearSelected)
        clearall      = findViewById(R.id.clearAll)

//       Beep Audio
        beepAudio = MediaPlayer.create(this,R.raw.beep)

        navbar = findViewById(R.id.navbar)
        navbar.selectedItemId = R.id.nav_delete




        //used to change between screens
        navbar.setOnItemSelectedListener {

            when (it.itemId) {

                //save icon current
                R.id.nav_save -> {
                    go_main = Intent(this, MainActivity::class.java)
                    go_main.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(go_main)
                    true
                }

                //view icon
                R.id.nav_view -> {
                    go_view = Intent(this, viewlist::class.java)
                    go_view.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(go_view)
                    true
                }

                //delete icon
                R.id.nav_delete -> {
                    true
                }

                else -> false

            }
        }

        //sets values to spinner
        spinner = findViewById(R.id.clearspinner)

        val slots = arrayOf("Slot 1","Slot 2","Slot 3","Slot 4","Slot 5")

        spinner.adapter = ArrayAdapter(this,R.layout.spinner_style, slots)

//      helped with running a function using spinner  https://www.geeksforgeeks.org/kotlin/spinner-in-kotlin/
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {

                slotOrder = (position + 1)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //nothing RAHHHH
            }
        }

        clearselected.setOnClickListener {
            deletestorage()
        }


        clearall.setOnClickListener {
            clearDATAWarning()
        }

    }

    fun deletestorage(){
        Toast.makeText(this, "Slot${slotOrder} data deleted", Toast.LENGTH_SHORT).show()

        storage.edit().remove("slot${slotOrder}_name").apply()
        storage.edit().remove("slot${slotOrder}_number").apply()
        storage.edit().remove("slot${slotOrder}_about").apply()

    }


    fun clearDATAWarning(){ //runs on clear all
        //first creates popup to warn user
        val displayer = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val popupView = displayer.inflate(R.layout.activity_warning_pop,null)

        val warningWindow = PopupWindow(popupView, 1070, 900, true)

        warningWindow.showAtLocation(popupView, Gravity.CENTER,0,0)

        val closeButton: Button = popupView.findViewById(R.id.close)
        val confirm: Button = popupView.findViewById(R.id.confirm)

        //play audio
        beepAudio.start()

        //closes popup
        closeButton.setOnClickListener{
            warningWindow.dismiss()
        }
        confirm.setOnClickListener {
            deleteDATA()
            warningWindow.dismiss()
            Toast.makeText(this, "All data deleted", Toast.LENGTH_SHORT).show()
        }

    }

    fun deleteDATA(){
        //edit storage. clear it and update it
        storage.edit().clear().apply()
    }


}