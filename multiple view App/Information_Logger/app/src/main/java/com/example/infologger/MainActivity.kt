package com.example.infologger

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navbar: BottomNavigationView
    private lateinit var go_remove: Intent
    private lateinit var go_view: Intent

//    text variables
    private lateinit var name: EditText
    private lateinit var number: EditText
    private lateinit var about: EditText

//    clean text. After save function
    private var userName = ""
    private var userNumber = ""
    private var userAbout = ""

//    storage variables
    private lateinit var spinner: Spinner
    private lateinit var saveBtn: Button
    private lateinit var storage: SharedPreferences

//    goto instructions variables
    private lateinit var gotoHelper: ImageButton
    private lateinit var go_help: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //sets values to spinner
        spinner = findViewById(R.id.spinner)

        val slots = arrayOf("Slot 1","Slot 2","Slot 3","Slot 4","Slot 5")

        spinner.adapter = ArrayAdapter(this,R.layout.spinner_style, slots)

        //sets values to user information content
        name =findViewById(R.id.personname)
        number =findViewById(R.id.personnumber)
        about =findViewById(R.id.personabout)
        saveBtn = findViewById(R.id.personSave)

        //storage
        storage = getSharedPreferences("userdata", MODE_PRIVATE)

//        helper menu btn
        gotoHelper = findViewById(R.id.gotoHelp)

        navbar = findViewById(R.id.navbar)
        navbar.selectedItemId = R.id.nav_save

        //used to change between screens
        navbar.setOnItemSelectedListener {

            when (it.itemId) {

                //save icon current
                R.id.nav_save -> {
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
                    go_remove = Intent(this, remove::class.java)
                    go_remove.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(go_remove)
                    true
                }
            else -> false

            }
        }

        saveBtn.setOnClickListener {
            saveText()
        }

        gotoHelper.setOnClickListener {
            go_help = Intent(this, instructions::class.java)
            go_help.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(go_help)
        }


    }

    fun saveText(){
//        gets information from edit text
        userName = name.text.toString()
        userNumber =number.text.toString()
        userAbout = about.text.toString()

//        stores spinner number (slots)
        val selectedSlot = spinner.selectedItemPosition + 1

        val editor = storage.edit()

//        key to value
        editor.putString("slot${selectedSlot}_name", userName)
        editor.putString("slot${selectedSlot}_number", userNumber)
        editor.putString("slot${selectedSlot}_about", userAbout)

        editor.apply()

        Toast.makeText(this, "Saved to Slot $selectedSlot", Toast.LENGTH_SHORT).show()
    }


}