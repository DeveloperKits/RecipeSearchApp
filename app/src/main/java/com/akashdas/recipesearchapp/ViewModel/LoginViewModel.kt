package com.akashdas.recipesearchapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> get() = _loginState

    fun signIn(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _loginState.value = LoginState.SUCCESS
            } else {
                _loginState.value = LoginState.FAILURE(task.exception?.message)
            }
        }
    }

    sealed class LoginState {
        object SUCCESS : LoginState()
        data class FAILURE(val message: String?) : LoginState()
    }
}
