package com.cathares.cryptoviewer.ui.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cathares.cryptoviewer.data.network.NetworkResult
import com.cathares.cryptoviewer.ui.state.TokenListUIState
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
        onListUpdate(chipState[tokenListUIState.value.chipSelected]!!)
    }

    private fun onListUpdate(currency: String) {
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

    fun onRetryButtonClick() {
        onListUpdate(chipState[tokenListUIState.value.chipSelected]!!)
    }
    fun onChipSwitch() {
        val chipSelected = _tokenListUIState.value.chipSelected
        _tokenListUIState.value = TokenListUIState(chipSelected = !chipSelected)
        onListUpdate(chipState[_tokenListUIState.value.chipSelected]!!)
    }
}