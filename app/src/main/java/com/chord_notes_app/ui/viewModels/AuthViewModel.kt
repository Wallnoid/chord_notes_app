package com.chord_notes_app.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chord_notes_app.data.AuthResponse
import com.chord_notes_app.data.Member
import com.chord_notes_app.data.UserResponse
import com.chord_notes_app.data.username
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

    fun logout(token:String, onResult:(Boolean) -> Unit ) {
        viewModelScope.launch {
            try {
                authRepository.logout(token)
                onResult(true)
            } catch (e: Exception) {
                println(e)
                onResult(false)
            }
        }
    }

    fun logoutAll(onResult:(Boolean) -> Unit ) {

        viewModelScope.launch {
            try {
                authRepository.logoutAll()
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun profile(onResult:(UserResponse?) -> Unit ) {
        viewModelScope.launch {
            try {
                val user = authRepository.profile()
                onResult(user)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }

    fun getUserByName(token: String, username: username, onResult:(Member?) -> Unit ) {
        viewModelScope.launch {
            try {
                val user = authRepository.getUserByName(token, username)
                onResult(user)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }


}
