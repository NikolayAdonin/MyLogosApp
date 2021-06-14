package com.example.logosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast

class RegActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var  etPassword: EditText
    var userName = ""
    var userEmail =""
    var userPassword=""
    val integerChars = '0'..'9'
    val MIN_PASSWORD_LENGTH = 6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        viewInitializations()
    }

    fun viewInitializations() {
        etName = findViewById(R.id.editTextPersonName)
        etEmail = findViewById(R.id.editTextPersonMail)
        etPassword = findViewById(R.id.editTextPersonPassword)

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun checkName(input:String):Boolean
    {
        var dotOccurred = 0
        return input.all { it in integerChars || it == '.' && dotOccurred++ < 1 }
    }

    fun validateInput(): Boolean {
        if(checkName(etName.text.toString())) {
            etName.error = "Имя не должно содержать символов кроме букв"
            return false
        }
        if (etEmail.text.toString() == "") {
            etEmail.error = "Введите Email"
            return false
        }
        if (etPassword.text.toString() == "") {
            etPassword.error = "Введите пароль"
            return false
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.error = "Введите правильный Email"
            return false
        }

        // checking minimum password Length
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.error = "Длина пароля должна быть больше $MIN_PASSWORD_LENGTH символов"
            return false
        }
        return true
    }
    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun FirstRegPress(view: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server
            val email = etEmail!!.text.toString()
            val password = etPassword!!.text.toString()
            userEmail = email
            userPassword = password
            Toast.makeText(this, "Вы авторизованы", Toast.LENGTH_SHORT).show()
            this.finish()
            // Here you can call you API
        }
        else
            Toast.makeText(this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show()
    }
}