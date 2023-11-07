package com.akashdas.recipesearchapp.Fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.akashdas.recipesearchapp.R
import com.akashdas.recipesearchapp.databinding.FragmentRegistrationBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.database

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        // Initialize Firebase Auth
        auth = Firebase.auth

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // progressDialog
        progressDialog = ProgressDialog(requireContext()).apply {
            setTitle("Registration in Progress")
            setMessage("Please wait while we are processing your registration.")
            isIndeterminate = true
            setCancelable(false)
        }

        // login button
        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        // registration button
        binding.registerButton.setOnClickListener {
            val email = binding.emailText.text.toString()
            val pass = binding.passText.text.toString()

            if(email.isNullOrBlank()){
                binding.emailText.error = "This field can't be empty"
            }else if (pass.isNullOrBlank()){
                binding.passText.error = "Enter a password"
            }else if (pass.length < 6){
                binding.passText.error = "Password length insufficient"
            }else if (!binding.checkBox.isChecked){
                Toast.makeText(
                    requireContext(),
                    "Must agree our terms and privacy policy",
                    Toast.LENGTH_SHORT)
                    .show()
            }else {
                progressDialog.show()
                createAccount(email, pass)
            }
        }
    }

    private fun createAccount(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {task ->
            if (task.isSuccessful) {
                // registration success
                val user = auth.currentUser
                addUserOnFireStore(user!!.uid, email, pass)

            } else {
                // If registratio fails, display a message to the user.
                progressDialog.dismiss()
                Toast.makeText(
                    requireContext(),
                    "Authentication failed. Try again",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }

    private fun addUserOnFireStore(uid: String, email: String, pass: String) {

        val database = Firebase.database
        val myRef = database.getReference("users").child(uid)

        // Create a new user
        val user = hashMapOf(
            "uid" to uid,
            "email" to email,
            "password" to pass,
        )

        myRef.setValue(user).addOnSuccessListener {
            Toast.makeText(
                requireContext(),
                "Registration successful",
                Toast.LENGTH_SHORT,
            ).show()

            progressDialog.dismiss()
            Firebase.auth.signOut()
            // go to login fragment
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
    }


}