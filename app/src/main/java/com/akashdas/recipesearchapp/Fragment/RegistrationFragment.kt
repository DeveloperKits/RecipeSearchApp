package com.akashdas.recipesearchapp.Fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akashdas.recipesearchapp.R
import com.akashdas.recipesearchapp.ViewModel.RegistrationViewModel
import com.akashdas.recipesearchapp.databinding.FragmentRegistrationBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.database

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var progressDialog: ProgressDialog
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
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

        // Observe ViewModel LiveData
        viewModel.registrationState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RegistrationViewModel.RegistrationState.SUCCESS -> {
                    progressDialog.dismiss()
                    Toast.makeText(requireContext(),
                        "Registration successful",
                        Toast.LENGTH_SHORT)
                        .show()

                    Firebase.auth.signOut()
                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                }
                is RegistrationViewModel.RegistrationState.FAILURE -> {
                    progressDialog.dismiss()
                    Toast.makeText(requireContext(),
                        "Authentication failed: ${state.message}",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
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
                createAccount(email, pass)
            }
        }
    }

    private fun createAccount(email: String, pass: String) {
        progressDialog.show()
        viewModel.createAccount(email, pass)
    }

}