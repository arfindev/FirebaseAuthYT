package com.example.firebaseauthyt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthyt.Data.AuthRepository
import com.example.firebaseauthyt.Data.SignUpState
import com.example.firebaseauthyt.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = Channel<SignUpState>()
    val authState = _authState.receiveAsFlow()


    fun firebaseAuth(email: String, password: String) = viewModelScope.launch {
        authRepository.registerUser(email, password).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _authState.send(SignUpState(isLoading = true))
                }
                is Resource.Success -> {
                    _authState.send(SignUpState(isSuccess = "Register Successful"))
                }
                is Resource.Error -> {
                    _authState.send(SignUpState(isError = result.message))
                }
            }


        }
    }

}
