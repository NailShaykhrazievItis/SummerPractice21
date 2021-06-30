package com.itis.summerpractice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val users = hashMapOf(
        "admin@admin.ru" to "12345",
        "test@test.com" to "qwe",
        "nail@admin.ru" to "qwe123",
    )

    private var etEmail: EditText? = null
    private var tiEmail: TextInputLayout? = null
    private var etPassword: EditText? = null
    private var tiPassword: TextInputLayout? = null
    private var btnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findView()
        initListeners()
    }

    private fun initListeners() {
        btnLogin?.setOnClickListener {
            val email = etEmail?.text?.toString() ?: ""
            val password = etPassword?.text?.toString() ?: ""

            val emailNotValid = if (email.isEmpty()) {
                tiEmail?.error = getString(R.string.empty_field)
                true
            } else {
                false
            }
            val passNotValid = if (password.isEmpty() && password.length > 2) {
                tiPassword?.error = getString(R.string.empty_field)
                true
            } else {
                false
            }

            if (passNotValid || emailNotValid)
                return@setOnClickListener

            val passFromBase: String? = users[email]
            val message = when {
                passFromBase == null -> getString(R.string.user_not_found)
                password == passFromBase -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("EXTRA_EMAIL", email)
                    intent.putExtra("EXTRA_PASS", password)
                    startActivity(intent)
                    "YOU are auth"
                }
                else -> getString(R.string.wrong_pass)
            }
            etEmail?.hideKeyboard(this)

            showMessage(message)
        }

        etEmail?.addTextChangedListener {
            tiEmail?.isErrorEnabled = false
            tiEmail?.error = null
        }

        etPassword?.addTextChangedListener {
            tiPassword?.isErrorEnabled = false
            tiPassword?.error = null
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun findView() {
        etEmail = findViewById(R.id.et_email)
        tiEmail = findViewById(R.id.ti_email)
        etPassword = findViewById(R.id.et_password)
        tiPassword = findViewById(R.id.ti_password)
        btnLogin = findViewById(R.id.btn_login)
    }
}
