package com.cathares.cryptoviewer.ui.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cathares.cryptoviewer.data.NetworkResult
import com.cathares.cryptoviewer.data.TokenListUIState
import com.cathares.cryptoviewer.data.repository.TokenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class TokenListViewModel(
    private val tokenRepository: TokenRepository
): ViewModel(){

    private val _tokenListUIState = MutableStateFlow(TokenListUIState())
    val tokenListUIState: StateFlow<TokenListUIState> = _tokenListUIState.asStateFlow()
    init {
        getTokens()
    }
    private fun getTokens() {
        _tokenListUIState.value = TokenListUIState(isLoading = true)
        viewModelScope.launch {
            when (val result = tokenRepository.getTokens()) {
                is NetworkResult.Success -> {
                    _tokenListUIState.update {
                        it.copy(isLoading = false, tokens = result.data)
                    }
                }
                is NetworkResult.Error -> {
                    _tokenListUIState.update {
                        Log.e("Error", result.error)
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }
}