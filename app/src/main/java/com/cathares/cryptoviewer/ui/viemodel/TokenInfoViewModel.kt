package com.cathares.cryptoviewer.ui.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cathares.cryptoviewer.data.network.NetworkResult
import com.cathares.cryptoviewer.ui.state.TokenInfoUIState
import com.cathares.cryptoviewer.data.repository.TokenInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TokenInfoViewModel(
    private val tokenInfoRepository: TokenInfoRepository
): ViewModel(){
    private val _tokenInfoUIState = MutableStateFlow(TokenInfoUIState())
    val tokenInfoUIState: StateFlow<TokenInfoUIState> = _tokenInfoUIState.asStateFlow()

    fun getInfo(name: String) {
        _tokenInfoUIState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            when (val result = tokenInfoRepository.getTokenInfo(name)) {
                is NetworkResult.Success -> {
                    _tokenInfoUIState.update {
                        it.copy(
                            isLoading = false,
                            tokenInfo = result.data,
                            description = result.data.description,
                            image = result.data.image
                        )
                    }
                }
                is NetworkResult.Error -> {
                    _tokenInfoUIState.update {
                        Log.e("ErrorNetwork", result.error)
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun retry(name: String) {
        getInfo(name)
    }
}