package com.chord_notes_app.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chord_notes_app.data.AuthResponse
import com.chord_notes_app.data.UserResponse
import com.chord_notes_app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun login(username: String, password: String, onResult:(AuthResponse?) -> Unit ) {
        viewModelScope.launch {
            try {
                val auth = authRepository.login(username, password)
                onResult(auth)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }

    fun register(username: String, password: String, email: String, onResult:(UserResponse?) -> Unit ) {
        viewModelScope.launch {
            try {
                val user = authRepository.register(username, password, email)
                println(user)
                onResult(user)
            } catch (e: Exception) {
                println(e)

                onResult(null)
            }
        }
    }
}
