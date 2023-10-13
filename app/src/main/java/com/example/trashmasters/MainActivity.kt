package com.example.trashmasters

import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val DontHaveAnAccount: TextView=findViewById(R.id.newuser)
        DontHaveAnAccount.setOnClickListener {
            val intent=Intent (this,RegistrationActivity::class.java)
            startActivity(intent)
        }
        val loginbtn: Button = findViewById(R.id.login)
        loginbtn.setOnClickListener{
            performLogin()
        }
    }

    private fun performLogin() {

        val email:EditText = findViewById(R.id.EmailLogin)
        val password:EditText =findViewById(R.id.Loginpassword)

        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"please fill all the fields",Toast.LENGTH_SHORT).show()
            return
        }

        val emailInput =email.text.toString()
        val passwordInput =password.text.toString()

        auth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        baseContext, "Login Succcessful...",
                        Toast.LENGTH_SHORT,
                    ).show()
                    val intent =Intent(this,home::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error occured ${it.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
    }
}