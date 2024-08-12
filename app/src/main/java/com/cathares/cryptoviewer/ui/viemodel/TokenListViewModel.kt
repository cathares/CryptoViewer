package com.cathares.cryptoviewer.ui.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cathares.cryptoviewer.data.network.NetworkResult
import com.cathares.cryptoviewer.data.TokenListUIState
import com.cathares.cryptoviewer.data.repository.TokenRepository
import com.cathares.cryptoviewer.util.chipState
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
        getTokens(chipState[tokenListUIState.value.chipSelected]!!)
    }

    private fun getTokens(currency: String) {
        _tokenListUIState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            when (val result = tokenRepository.getTokens(currency)) {
                is NetworkResult.Success -> {
                    _tokenListUIState.update {
                        it.copy(isLoading = false, tokens = result.data)
                    }
                }
                is NetworkResult.Error -> {
                    _tokenListUIState.update {
                        Log.e("ErrorNetwork", result.error)
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun retry() {
        getTokens(chipState[tokenListUIState.value.chipSelected]!!)
    }
    fun switchChip() {
        val chipSelected = _tokenListUIState.value.chipSelected
        _tokenListUIState.value = TokenListUIState(chipSelected = !chipSelected)
        getTokens(chipState[_tokenListUIState.value.chipSelected]!!)
    }
}