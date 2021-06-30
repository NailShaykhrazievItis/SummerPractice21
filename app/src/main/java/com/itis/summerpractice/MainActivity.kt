package com.itis.summerpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvPass: TextView? = null
    private var tvEmail: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email: String? = intent?.extras?.getString("EXTRA_EMAIL")
        val pass: String? = intent?.extras?.getString("EXTRA_PASS")

        tvEmail = findViewById(R.id.tv_email)
        tvPass = findViewById(R.id.tv_pass)

        if (email != null) {
            tvEmail?.text = "Email: $email"
        }
        if (pass != null) {
            tvPass?.text = "Password: $pass"
        }
    }
}
