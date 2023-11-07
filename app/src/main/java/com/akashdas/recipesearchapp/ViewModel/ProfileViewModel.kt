package com.akashdas.recipesearchapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ProfileViewModel : ViewModel() {

    private val database: DatabaseReference by lazy { Firebase.database.reference }
    private val auth: FirebaseAuth by lazy { Firebase.auth }

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> get() = _userData

    fun readDataFromFirebase() {
        val user = auth.currentUser
        user?.let {
            val myRef = database.child("users").child(it.uid)

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val email = dataSnapshot.child("email").getValue(String::class.java)
                        val password = dataSnapshot.child("password").getValue(String::class.java)
                        val uid = dataSnapshot.child("uid").getValue(String::class.java)
                        _userData.value = UserData(email, password, uid)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAG", "Failed to read value.", error.toException())
                }
            })
        }
    }

    data class UserData(
        val email: String?,
        val password: String?,
        val uid: String?
    )
}
