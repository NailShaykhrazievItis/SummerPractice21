package com.itis.summerpractice.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.itis.summerpractice.R

class MainFragment : Fragment(R.layout.fragment_main) {

    private var tvPass: TextView? = null
    private var tvEmail: TextView? = null
    private var btnSettings: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvEmail = view.findViewById(R.id.tv_email)
        tvPass = view.findViewById(R.id.tv_pass)
        btnSettings = view.findViewById(R.id.btn_settings)


        val email: String? = arguments?.getString(ARG_EMAIL)
        val pass: String? = arguments?.getString(ARG_PASS)

        if (email != null) {
            tvEmail?.text = "Email: $email"
        }
        if (pass != null) {
            tvPass?.text = "Password: $pass"
        }

        btnSettings?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
        }
    }

    companion object {

        private const val ARG_EMAIL = "ARG_EMAIL"
        private const val ARG_PASS = "ARG_PASS"

        fun createBundle(email: String, pass: String): Bundle = Bundle().apply {
            putString(ARG_EMAIL, email)
            putString(ARG_PASS, pass)
        }
    }
}
