package com.example.tadcships

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Buttons
        val jaxBtn = findViewById<ImageButton>(R.id.jax)
        val pomniBtn = findViewById<ImageButton>(R.id.pomni)
        val gangleBtn = findViewById<ImageButton>(R.id.gangle)
        val zoobleBtn = findViewById<ImageButton>(R.id.zooble)
        val kingerBtn = findViewById<ImageButton>(R.id.kinger)
        val ragathaBtn = findViewById<ImageButton>(R.id.ragatha)
        val helperbtn = findViewById<ImageButton>(R.id.helper)
        val matchBtn = findViewById<Button>(R.id.match_btn)

        //selection audio
        val clickSound = MediaPlayer.create(this, R.raw.selectionsound)

        //disable default audio
        gangleBtn.isSoundEffectsEnabled = false
        jaxBtn.isSoundEffectsEnabled = false
        zoobleBtn.isSoundEffectsEnabled = false
        ragathaBtn.isSoundEffectsEnabled = false
        pomniBtn.isSoundEffectsEnabled = false
        kingerBtn.isSoundEffectsEnabled = false

        // Removes Button effect/animation on click
        jaxBtn.setBackgroundResource(0)
        zoobleBtn.setBackgroundResource(0)
        gangleBtn.setBackgroundResource(0)
        kingerBtn.setBackgroundResource(0)
        pomniBtn.setBackgroundResource(0)
        ragathaBtn.setBackgroundResource(0)


//      Buttons used
        var usedjax: Boolean = false
        var usedpomni: Boolean = false
        var usedgangle: Boolean = false
        var usedzooble: Boolean = false
        var usedkinger: Boolean = false
        var usedragatha: Boolean = false

        var first: Boolean = false
        var second: Boolean = false
        var firstChar: String = ""
        var secondChar: String = ""

        //results variables (after match)
        val nicknamedisplayer: TextView = findViewById(R.id.nicknameDisplayer)
        val textdisplayer: TextView = findViewById(R.id.textDisplayer2)
        val namedis1: TextView = findViewById(R.id.namedis1)
        val namedis2: TextView = findViewById(R.id.namedis2)
        val image1displayer: ImageView = findViewById(R.id.image1)
        val image2displayer: ImageView = findViewById(R.id.image2)
        val bar: ProgressBar = findViewById(R.id.loverate)
        val nextBtn: Button = findViewById(R.id.next)

        //displays red or blue border around image button and stores information
        fun highlight(btn: ImageButton, name: String, used: Boolean, setUsed: (Boolean) -> Unit){
            //Lambda passed through a function to change used variable
            if (!used) //false
            {
                if (!first)
                {
                    setUsed(true)
                    firstChar = name
                    first = true
                    btn.setBackgroundResource(R.drawable.border_red)
                    btn.backgroundTintList = null

                    btn.scaleX = 1.1f
                    btn.scaleY = 1.1f
                }
                else if (!second)
                {
                    setUsed(true)
                    secondChar = name
                    second = true
                    btn.setBackgroundResource(R.drawable.border_blue)
                    btn.backgroundTintList = null

                    btn.scaleX = 1.1f
                    btn.scaleY = 1.1f
                }
            }
            else
            {
                if (firstChar == name)
                {
                    firstChar = ""
                    first = false
                    setUsed(false)
                    //border and tint
                    btn.setBackgroundResource(0)
                    btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFFFFF"))

                    btn.scaleX = 1.0f
                    btn.scaleY = 1.0f
                }

                else if (secondChar == name)
                {
                    secondChar = ""
                    second = false
                    setUsed(false)
                    //border and tint
                    btn.setBackgroundResource(0)
                    btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFFFFF"))

                    btn.scaleX = 1.0f
                    btn.scaleY = 1.0f
                }
            }
        }

        //displays instructions menu on click
        helperbtn.setOnClickListener{
            val displayer = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val popupView = displayer.inflate(R.layout.activity_helper,null)

            val HelperWindow = PopupWindow(popupView, 1070, 1600, true)

            HelperWindow.showAtLocation(popupView, Gravity.TOP,0,250)

            val closeButton: ImageButton = popupView.findViewById(R.id.close)
            closeButton.setOnClickListener{
                HelperWindow.dismiss()
            }
        }


        //highligh and save on click
        kingerBtn.setOnClickListener {
            highlight(kingerBtn,"Kinger",usedkinger) {usedkinger = it}
            clickSound.start()
        }
        zoobleBtn.setOnClickListener {
            highlight(zoobleBtn,"Zooble",usedzooble) {usedzooble = it}
            clickSound.start()
        }
        gangleBtn.setOnClickListener {
            highlight(gangleBtn,"Gangle",usedgangle) {usedgangle = it}
            clickSound.start()
        }
        jaxBtn.setOnClickListener {
            highlight(jaxBtn,"Jax",usedjax) {usedjax = it}
            clickSound.start()
        }
        pomniBtn.setOnClickListener {
            highlight(pomniBtn,"Pomni",usedpomni) {usedpomni = it}
            clickSound.start()
        }
        ragathaBtn.setOnClickListener {
            highlight(ragathaBtn,"Ragatha",usedragatha) {usedragatha = it}
            clickSound.start()
        }

        //information of each character pairs. using keys and values
        //information from https://shipping.fandom.com/wiki/The_Amazing_Digital_Circus
        //pomni ♥ + others
        val PomnixJax = mapOf(
            "nickname" to "FunnyBunny",
            "image1" to R.drawable.pomni,
            "image2" to R.drawable.jax,
            "name1" to "Pomni",
            "name2" to "Jax",
            "barProgress" to 99,
            "text" to "Chaotic prankster meets anxious newcomer, creating a wild mix of teasing, confusion, and unexpected chemistry."
        )
        val PomniXRagatha = mapOf(
            "nickname" to "Ragapom",
            "image1" to R.drawable.pomni,
            "image2" to R.drawable.ragatha,
            "name1" to "Pomni",
            "name2" to "Ragatha",
            "barProgress" to 80,
            "text" to "Kind and supportive, Ragatha comforts Pomni, forming a wholesome bond built on care and emotional support."
        )
        val PomniXGangle  = mapOf(
            "nickname" to "ScaredyLovers",
            "image1" to R.drawable.pomni,
            "image2" to R.drawable.gangle,
            "name1" to "Pomni",
            "name2" to "Gangle",
            "barProgress" to 60,
            "text" to "Two anxious personalities connect through shared fear, awkward moments, and mutual understanding of their strange situation."
        )
        val PomniXZooble  = mapOf(
            "nickname" to "AbstractComedy",
            "image1" to R.drawable.pomni,
            "image2" to R.drawable.zooble,
            "name1" to "Pomni",
            "name2" to "Zooble",
            "barProgress" to 55,
            "text" to "Zooble’s chaotic nature clashes with Pomni’s confusion, creating a strange but entertaining and unpredictable dynamic."
        )
        val PomniXKinger  = mapOf(
            "nickname" to "Kinglebells",
            "image1" to R.drawable.pomni,
            "image2" to R.drawable.kinger,
            "name1" to "Pomni",
            "name2" to "Kinger",
            "barProgress" to 60,
            "text" to "Kinger’s unstable personality adds mystery, while Pomni reacts with confusion and curiosity to his unpredictable behavior."
        )
        //kinger + others
        val KingerXGangle  = mapOf(
            "nickname" to "RoyalRibbons",
            "image1" to R.drawable.kinger,
            "image2" to R.drawable.gangle,
            "name1" to "Kinger",
            "name2" to "Gangle",
            "barProgress" to 55,
            "text" to "Both emotionally fragile, they share moments of vulnerability, creating a soft yet slightly chaotic connection."
        )
        val KingerXJax  = mapOf(
            "nickname" to "KingRabbit",
            "image1" to R.drawable.kinger,
            "image2" to R.drawable.jax,
            "name1" to "Kinger",
            "name2" to "Jax",
            "barProgress" to 30,
            "text" to "Jax’s teasing nature contrasts with Kinger’s instability, leading to chaotic interactions and unpredictable energy."
        )
        val KingerXZooble  = mapOf(
            "nickname" to "Zoobler",
            "image1" to R.drawable.kinger,
            "image2" to R.drawable.zooble,
            "name1" to "Kinger",
            "name2" to "Zooble",
            "barProgress" to 45,
            "text" to "Both unpredictable, their interactions feel chaotic and abstract, with little stability but lots of strange energy."
        )
        val KingerXRagatha  = mapOf(
            "nickname" to "DollChess",
            "image1" to R.drawable.kinger,
            "image2" to R.drawable.ragatha,
            "name1" to "Kinger",
            "name2" to "Ragatha",
            "barProgress" to 73,
            "text" to "Ragatha’s kindness balances Kinger’s instability, creating a caring dynamic with moments of emotional grounding."
        )
        //Ragatha
        val RagathaXJax  = mapOf(
            "nickname" to "BunnyDoll",
            "image1" to R.drawable.ragatha,
            "image2" to R.drawable.jax,
            "name1" to "Ragatha",
            "name2" to "Jax",
            "barProgress" to 64,
            "text" to "Playful teasing meets patience, as Ragatha handles Jax’s antics with calmness and subtle understanding."
        )
        val RagathaXGangle  = mapOf(
            "nickname" to "RibbonRags",
            "image1" to R.drawable.ragatha,
            "image2" to R.drawable.gangle,
            "name1" to "Ragatha",
            "name2" to "Gangle",
            "barProgress" to 88,
            "text" to "A gentle and supportive pairing, with Ragatha helping Gangle through emotional struggles and insecurity."
        )
        val RagathaXZooble  = mapOf(
            "nickname" to "RagaZoob",
            "image1" to R.drawable.ragatha,
            "image2" to R.drawable.zooble,
            "name1" to "Ragatha",
            "name2" to "Zooble",
            "barProgress" to 60,
            "text" to "Kindness meets chaos, creating an odd but interesting balance between stability and unpredictable behavior."
        )
        //Jax
        val JaxXGangle  = mapOf(
            "nickname" to "Ribbun",
            "image1" to R.drawable.jax,
            "image2" to R.drawable.gangle,
            "name1" to "Jas",
            "name2" to "Gangle",
            "barProgress" to 45,
            "text" to "Jax’s teasing often overwhelms Gangle, but their interactions still carry a weird, entertaining tension."
        )
        val JaxXZooble  = mapOf(
            "nickname" to "Abstrabbit",
            "image1" to R.drawable.jax,
            "image2" to R.drawable.zooble,
            "name1" to "Jax",
            "name2" to "Zooble",
            "barProgress" to 40,
            "text" to "Two chaotic personalities clash and match, creating unpredictable, humorous, and energetic interactions."
        )
        //closer
        val GangleXZooble  = mapOf(
            "nickname" to "Abstragedy",
            "image1" to R.drawable.gangle,
            "image2" to R.drawable.zooble,
            "name1" to "Gangle",
            "name2" to "Zooble",
            "barProgress" to 75,
            "text" to "Emotional sensitivity meets chaotic abstraction, forming a strange yet oddly balanced dynamic."
        )

//help with animation https://stackoverflow.com/questions/30766755/smooth-progress-bar-animation/30766886
        fun animateProgressBar(final: Int){
            val animator = ObjectAnimator.ofInt(bar, "progress", 0, final)
            animator.duration = 1000
            animator.start()
        }

        fun update_page(dict: Map<String, Any>){
            //Images
            image1displayer.setImageResource(dict["image1"] as Int)
            image2displayer.setImageResource(dict["image2"] as Int)
            //nickname
            nicknamedisplayer.text = "Nickname: ${dict["nickname"] as String}"
            //Text
            textdisplayer.text = (dict["text"] as String)
            namedis1.text = (dict["name1"] as String)
            namedis2.text = (dict["name2"] as String)
            //progress bar animation
            animateProgressBar(dict["barProgress"] as Int)
        }

        fun startMatch(){
            if(first && second){
                //hides all selection items and menu
                jaxBtn.visibility = View.GONE
                pomniBtn.visibility = View.GONE
                gangleBtn.visibility = View.GONE
                zoobleBtn.visibility = View.GONE
                kingerBtn.visibility = View.GONE
                ragathaBtn.visibility = View.GONE
                helperbtn.visibility = View.GONE
                matchBtn.visibility = View.GONE

                //update text and images
                if((firstChar == "Pomni" && secondChar == "Jax") || (firstChar == "Jax" && secondChar == "Pomni")){
                    update_page(PomnixJax)
                }
                else if((firstChar == "Pomni" && secondChar == "Ragatha") || (firstChar == "Ragatha" && secondChar == "Pomni")){
                    update_page(PomniXRagatha)
                }
                else if((firstChar == "Pomni" && secondChar == "Gangle") || (firstChar == "Gangle" && secondChar == "Pomni")){
                    update_page(PomniXGangle)
                }
                else if((firstChar == "Pomni" && secondChar == "Zooble") || (firstChar == "Zooble" && secondChar == "Pomni")){
                    update_page(PomniXZooble)
                }
                else if((firstChar == "Pomni" && secondChar == "Kinger") || (firstChar == "Kinger" && secondChar == "Pomni")){
                    update_page(PomniXKinger)
                }
                else if((firstChar == "Kinger" && secondChar == "Gangle") || (firstChar == "Gangle" && secondChar == "Kinger")){
                    update_page(KingerXGangle)
                }
                else if((firstChar == "Kinger" && secondChar == "Jax") || (firstChar == "Jax" && secondChar == "Kinger")){
                    update_page(KingerXJax)
                }
                else if((firstChar == "Kinger" && secondChar == "Zooble") || (firstChar == "Zooble" && secondChar == "Kinger")){
                    update_page(KingerXZooble)
                }
                else if((firstChar == "Kinger" && secondChar == "Ragatha") || (firstChar == "Ragatha" && secondChar == "Kinger")){
                    update_page(KingerXRagatha)
                }
                else if((firstChar == "Ragatha" && secondChar == "Jax") || (firstChar == "Jax" && secondChar == "Ragatha")){
                    update_page(RagathaXJax)
                }
                else if((firstChar == "Ragatha" && secondChar == "Gangle") || (firstChar == "Gangle" && secondChar == "Ragatha")){
                    update_page(RagathaXGangle)
                }
                else if((firstChar == "Ragatha" && secondChar == "Zooble") || (firstChar == "Zooble" && secondChar == "Ragatha")){
                    update_page(RagathaXZooble)
                }
                else if((firstChar == "Jax" && secondChar == "Gangle") || (firstChar == "Gangle" && secondChar == "Jax")){
                    update_page(JaxXGangle)
                }
                else if((firstChar == "Jax" && secondChar == "Zooble") || (firstChar == "Zooble" && secondChar == "Jax")){
                    update_page(JaxXZooble)
                }
                else if((firstChar == "Gangle" && secondChar == "Zooble") || (firstChar == "Zooble" && secondChar == "Gangle")){
                    update_page(GangleXZooble)
                }

                //Shows results items & menu
                nicknamedisplayer.visibility = View.VISIBLE
                image1displayer.visibility = View.VISIBLE
                image2displayer.visibility = View.VISIBLE
                textdisplayer.visibility = View.VISIBLE
                nextBtn.visibility = View.VISIBLE
                bar.visibility = View.VISIBLE
                namedis2.visibility = View.VISIBLE
                namedis1.visibility = View.VISIBLE



            }
            else{
                Toast.makeText(this, "Please Select 2 Characters", Toast.LENGTH_SHORT).show()
            }

        }
        fun reverse(){
            //reset variable

            //hides results items & menu
            nicknamedisplayer.visibility = View.GONE
            image1displayer.visibility = View.GONE
            image2displayer.visibility = View.GONE
            textdisplayer.visibility = View.GONE
            nextBtn.visibility = View.GONE
            bar.visibility = View.GONE
            namedis2.visibility = View.GONE
            namedis1.visibility = View.GONE

            //shows all selection items and menu
            jaxBtn.visibility = View.VISIBLE
            pomniBtn.visibility = View.VISIBLE
            gangleBtn.visibility = View.VISIBLE
            zoobleBtn.visibility = View.VISIBLE
            kingerBtn.visibility = View.VISIBLE
            ragathaBtn.visibility = View.VISIBLE
            helperbtn.visibility = View.VISIBLE
            matchBtn.visibility = View.VISIBLE


        }




        matchBtn.setOnClickListener {
            startMatch()
        }
        nextBtn.setOnClickListener {
            reverse()
        }


    }



}