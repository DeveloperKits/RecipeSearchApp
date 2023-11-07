package com.akashdas.recipesearchapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.akashdas.recipesearchapp.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        lifecycleScope.launch {
            delay(2000)
            val currentUser = auth.currentUser

            // if auth user are available then go home otherwise registration fragment
            if (currentUser != null) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
            }else {
                findNavController().navigate(R.id.action_splashScreenFragment_to_registrationFragment)
            }

        }
    }
}