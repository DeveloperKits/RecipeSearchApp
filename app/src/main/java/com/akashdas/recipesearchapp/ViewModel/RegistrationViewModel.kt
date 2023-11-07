package com.akashdas.recipesearchapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class RegistrationViewModel : ViewModel() {

    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val database: DatabaseReference by lazy { Firebase.database.reference }

    private val _registrationState = MutableLiveData<RegistrationState>()
    val registrationState: LiveData<RegistrationState> get() = _registrationState

    fun createAccount(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                addUserOnFireStore(user!!.uid, email, pass)
            } else {
                _registrationState.value = RegistrationState.FAILURE(task.exception?.message)
            }
        }
    }

    private fun addUserOnFireStore(uid: String, email: String, pass: String) {
        val user = hashMapOf(
            "uid" to uid,
            "email" to email,
            "password" to pass
        )

        database.child("users").child(uid).setValue(user).addOnSuccessListener {
            _registrationState.value = RegistrationState.SUCCESS
        }.addOnFailureListener {
            _registrationState.value = RegistrationState.FAILURE(it.message)
        }
    }

    sealed class RegistrationState {
        object SUCCESS : RegistrationState()
        data class FAILURE(val message: String?) : RegistrationState()
    }
}
