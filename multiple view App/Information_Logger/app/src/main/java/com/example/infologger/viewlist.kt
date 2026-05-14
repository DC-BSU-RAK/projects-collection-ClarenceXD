package com.example.infologger

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.util.Log

class viewlist : AppCompatActivity() {
    private lateinit var navbar: BottomNavigationView
    private lateinit var go_remove: Intent
    private lateinit var go_main: Intent

    private lateinit var spinner: Spinner

    private lateinit var displayName: TextView
    private lateinit var displayNumber: TextView
    private lateinit var displayAbout: TextView
    private lateinit var storage: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_viewlist)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        displayName   = findViewById(R.id.displayName)
        displayNumber = findViewById(R.id.displayNumber)
        displayAbout  = findViewById(R.id.displayAbout)


        storage = getSharedPreferences("userdata", MODE_PRIVATE)

        navbar = findViewById(R.id.navbar)
        navbar.selectedItemId = R.id.nav_view

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
                    true
                }

                //delete icon
                R.id.nav_delete -> {
                    go_remove = Intent(this, remove::class.java)
                    go_remove.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(go_remove)
                    true
                }
                else -> false

            }
        }

        //sets values to spinner
        spinner = findViewById(R.id.Viewspinner)

        val slots = arrayOf("Slot 1","Slot 2","Slot 3","Slot 4","Slot 5")

        spinner.adapter = ArrayAdapter(this,R.layout.spinner_style, slots)

//      helped with running a function using spinner  https://www.geeksforgeeks.org/kotlin/spinner-in-kotlin/
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {
                //function to update display
                displayInformation(position + 1)

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //nothing RAHHHH
            }
        }


    }

    //gets string values from storage and display it on screen
    fun displayInformation(position: Int){

        displayName.text = storage.getString("slot${position}_name","")
        displayNumber.text = storage.getString("slot${position}_number","")
        displayAbout.text =  storage.getString("slot${position}_about","")
    }
}