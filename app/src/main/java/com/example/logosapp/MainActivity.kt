package com.example.logosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun LoginPress(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val loginIntent = Intent(this,LoginActivity::class.java)
        startActivity(loginIntent)
        // myToast.show()
    }

    fun RegPress(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val regIntent = Intent(this,RegActivity::class.java)
        startActivity(regIntent)
        // myToast.show()
    }

}