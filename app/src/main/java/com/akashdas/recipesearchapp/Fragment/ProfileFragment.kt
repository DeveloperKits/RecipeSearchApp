package com.akashdas.recipesearchapp.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akashdas.recipesearchapp.R
import com.akashdas.recipesearchapp.ViewModel.ProfileViewModel
import com.akashdas.recipesearchapp.databinding.FragmentProfileBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // read data from firebase
        viewModel.userData.observe(viewLifecycleOwner) { userData ->
            binding.emails.text = "Email: ${userData.email}"
            binding.password.text = "Password: ${userData.password}"
            // UID is not displayed to the user
        }
        // Fetch data
        viewModel.readDataFromFirebase()

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.login.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

    private fun readDataFromFirebase() {
        val database = Firebase.database
        val auth = Firebase.auth
        val user = auth.currentUser
        val myRef = database.getReference("users").child(user!!.uid)

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get the user data from the dataSnapshot
                    val email = dataSnapshot.child("email").getValue(String::class.java)
                    val password = dataSnapshot.child("password").getValue(String::class.java)
                    val uid = dataSnapshot.child("uid").getValue(String::class.java)

                    // Now can use the email, password, and uid in your app
                    binding.emails.text = "Email: $email"
                    binding.password.text = "Password: $password"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
    }
}