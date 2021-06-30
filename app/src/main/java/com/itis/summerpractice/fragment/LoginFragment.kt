package com.itis.summerpractice.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.itis.summerpractice.R
import com.itis.summerpractice.hideKeyboard

class LoginFragment : Fragment(R.layout.fragment_login) {

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
    private var btnSettings: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findView(view)
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
                    findNavController().navigate(
                        R.id.action_loginFragment_to_mainFragment,
                        MainFragment.createBundle(email, password)
                    )
                    "YOU are auth"
                }
                else -> getString(R.string.wrong_pass)
            }
            etEmail?.hideKeyboard(requireContext())

            showMessage(message)
        }

        btnSettings?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_settingsFragment)
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
       activity?.findViewById<View>(android.R.id.content)?.also {
            Snackbar.make(
                it,
                message,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun findView(view: View) {
        etEmail = view.findViewById(R.id.et_email)
        tiEmail = view.findViewById(R.id.ti_email)
        etPassword = view.findViewById(R.id.et_password)
        tiPassword = view.findViewById(R.id.ti_password)
        btnLogin = view.findViewById(R.id.btn_login)
        btnSettings = view.findViewById(R.id.btn_settings)
    }
}
