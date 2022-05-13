package com.example.weatherproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.weatherproject.databinding.ActivityMainBinding

class Menu_Activity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)



        findViewById<ImageButton>(R.id.aboutus_button).setOnClickListener{
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.faq_button).setOnClickListener{
            val intent = Intent(this, FAQActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.home123_button).setOnClickListener{
            val intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
        }






    }
}