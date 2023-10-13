package com.example.trashmasters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth=FirebaseAuth.getInstance()

        val AlreadyHaveAnAccount: TextView=findViewById(R.id.AlreadyHaveAnAccount)
        AlreadyHaveAnAccount.setOnClickListener {
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val registerBtn: Button= findViewById(R.id.register)
        registerBtn.setOnClickListener {
            performAuth()
        }



    }

    private fun performAuth() {
        val email=findViewById<EditText>(R.id.emailId)
        val password=findViewById<EditText>(R.id.rpasw)
        val confirmPassword=findViewById<EditText>(R.id.rcpas)

        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT)
                .show()
            return
        }


        val inputEmail=email.text.toString()
        val inputPassword=password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        baseContext, "Registration Succcessful...",
                        Toast.LENGTH_SHORT,
                    ).show()
                    val intent =Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error occured ${it.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
    }
}