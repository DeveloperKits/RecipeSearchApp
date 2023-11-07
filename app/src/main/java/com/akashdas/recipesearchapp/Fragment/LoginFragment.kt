package com.akashdas.recipesearchapp.Fragment

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.akashdas.recipesearchapp.R
import com.akashdas.recipesearchapp.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Initialize Firebase Auth
        auth = Firebase.auth

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // progressDialog
        progressDialog = ProgressDialog(requireContext()).apply {
            setTitle("Login Progress")
            setMessage("Please wait while we are processing your login.")
            isIndeterminate = true
            setCancelable(false)
        }

        // go to registration
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        // login
        binding.loginButton.setOnClickListener {
            val email = binding.emailText.text.toString()
            val pass = binding.passText.text.toString()

            if(email.isNullOrBlank()){
                binding.emailText.error = "This field can't be empty"
            }else if (pass.isNullOrBlank()){
                binding.passText.error = "Enter a password"
            }else if (pass.length < 6){
                binding.passText.error = "Password length insufficient"
            }else {
                progressDialog.show()
                checkAccountAndSignIn(email, pass)
            }
        }
    }

    private fun checkAccountAndSignIn(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    progressDialog.dismiss()
                    Toast.makeText(
                        requireContext(),
                        "Login successful",
                        Toast.LENGTH_SHORT,
                    ).show()

                    // go to home fragment
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss()
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed. Try with another mail",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}