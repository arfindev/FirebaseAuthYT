package com.example.firebaseauthyt.Data


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthyt.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = Channel<SignInState>()
    val authState = _authState.receiveAsFlow()


    fun firebaseSignIn(email: String, password: String) = viewModelScope.launch {
        authRepository.loginUser(email, password).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _authState.send(SignInState(isLoading = true))
                }
                is Resource.Success -> {
                    _authState.send(SignInState(isSuccess = "Signin Successful"))
                }
                is Resource.Error -> {
                    _authState.send(SignInState(isError = result.message))
                }
            }


        }
    }

}
