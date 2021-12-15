package com.example.logosapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

class LoginActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var  etPassword: EditText
    var userEmail =""
    var userPassword=""
    val MIN_PASSWORD_LENGTH = 6
    val testMail = "test@gmail.com"
    val testPass = "test123"
    internal lateinit var conn: Connection
    private var username = "root"
    private var password = "root"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        App.user = testMail

        val connectionProps = Properties()
        connectionProps.put("user", username)
        connectionProps.put("password", password)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/logos", connectionProps)
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }

        viewInitializations()
    }

    fun viewInitializations() {
        etEmail = findViewById(R.id.editTextEmailAddress)
        etPassword = findViewById(R.id.editTextPassword)
        etEmail.setText(testMail)
        etPassword.setText(testPass)
        // показать кнопку назад
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun validateInput(): Boolean {
        if (etEmail.text.toString() == "") {
            etEmail.error = "Введите Email"
            return false
        }
        if (etPassword.text.toString() == "") {
            etPassword.error = "Введите пароль"
            return false
        }

        // проверить правильность Email
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.error = "Введите правильный Email"
            return false
        }

        // проверка на миимальную длину пароля
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.error = "Длина пароля должна быть больше " + MIN_PASSWORD_LENGTH + " символов"
            return false
        }
        return true
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun LoginPress(view: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server
            val email = etEmail!!.text.toString()
            val password = etPassword!!.text.toString()
            userEmail = email
            userPassword = password
            if(userEmail=="test@gmail.com" && userPassword == "test123")
            {
                //Toast.makeText(this, "Вы авторизованы", Toast.LENGTH_SHORT).show()
                this.finish()
                val fragmetsIntent = Intent(this,FragmentsActivity::class.java)
                startActivity(fragmetsIntent)
            }
            // Here you can call you API
        }
        else
            Toast.makeText(this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show()
    }
}