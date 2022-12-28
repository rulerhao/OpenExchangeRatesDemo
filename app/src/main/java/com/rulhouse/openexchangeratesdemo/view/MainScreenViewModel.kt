package com.rulhouse.openexchangeratesdemo.view

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.use_cases.RatesPreferencesDataStoreUseCases
import com.rulhouse.openexchangeratesdemo.local.room.domain.use_case.RatesPersistUseCases
import com.rulhouse.openexchangeratesdemo.remote.rates.di.RatesApiModule.appId
import com.rulhouse.openexchangeratesdemo.remote.rates.di.RatesApiModule.base
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.Rate
import com.rulhouse.openexchangeratesdemo.remote.rates.use_cases.RatesApiUseCases
import com.rulhouse.openexchangeratesdemo.remote.response.BaseResult
import com.rulhouse.openexchangeratesdemo.view.components.currency_button.CurrencyButtonEvent
import com.rulhouse.openexchangeratesdemo.view.components.dialog.ErrorDialogEvent
import com.rulhouse.openexchangeratesdemo.view.components.dialog.ErrorDialogState
import com.rulhouse.openexchangeratesdemo.view.components.drop_down_menu.DropdownMenuEvent
import com.rulhouse.openexchangeratesdemo.view.components.drop_down_menu.DropdownMenuState
import com.rulhouse.openexchangeratesdemo.view.components.input_textfield.InputTextFieldEvent
import com.rulhouse.openexchangeratesdemo.view.components.input_textfield.InputTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val ratesApiUseCases: RatesApiUseCases,
    private val ratesPersistUseCases: RatesPersistUseCases,
    private val preferencesUseCases: RatesPreferencesDataStoreUseCases
) : ViewModel() {

    private val _rate = mutableStateOf(Rate(currency = "USD", value = 1.0))
    val rate: State<Rate> = _rate

    private val _rates: MutableState<List<Rate>> = mutableStateOf(emptyList())
    val rates: State<List<Rate>> = _rates

    private val _dropdownMenuState = mutableStateOf(DropdownMenuState())
    val dropdownMenuState: State<DropdownMenuState> = _dropdownMenuState

    private val _inputTextFieldState = mutableStateOf(InputTextFieldState())
    val inputTextFieldState: State<InputTextFieldState> = _inputTextFieldState

    private val _errorDialogState = mutableStateOf(ErrorDialogState())
    val errorDialogState: State<ErrorDialogState> = _errorDialogState

    private val _lastFetchTimestamp: MutableState<Long> = mutableStateOf(0)
    val lastFetchTimestamp: State<Long> = _lastFetchTimestamp

    private var getRatesJob: Job? = null

    private var getPreferencesJob: Job? = null

    init {
        viewModelScope.launch {
            getRatesPreferences()
        }
        viewModelScope.launch {
            getLastFetchTimeStamp()
        }
        viewModelScope.launch {
            getPersistRates()
        }
    }

    fun onDropdownMenuEvent(event: DropdownMenuEvent) {
        when (event) {
            is DropdownMenuEvent.OnRateSelect -> {
                _dropdownMenuState.value = dropdownMenuState.value.copy(expand = false)
                onRateChanged(event.rate)
            }
            is DropdownMenuEvent.OnDismissRequest -> {
                _dropdownMenuState.value = dropdownMenuState.value.copy(expand = false)
            }
            is DropdownMenuEvent.OnExpand -> {
                _dropdownMenuState.value = dropdownMenuState.value.copy(expand = true)
            }
        }
    }

    fun onCurrencyButtonEvent(event: CurrencyButtonEvent) {
        when (event) {
            is CurrencyButtonEvent.OnButtonClick -> {
                onRateChanged(event.rate)
            }
        }
    }

    fun onInputTextFieldEvent(event: InputTextFieldEvent) {
        when (event) {
            is InputTextFieldEvent.OnValueChange -> {
                _inputTextFieldState.value = inputTextFieldState.value.copy(
                    value = event.text,
                    isNumeric = InputTextFieldState().isNumeric(event.text)
                )
            }
        }
    }

    fun onErrorDialogEvent(event: ErrorDialogEvent) {
        when (event) {
            ErrorDialogEvent.OnDismiss -> {
                _errorDialogState.value = errorDialogState.value.copy(isShow = false)
            }
        }
    }

    private fun onRateChanged(rate: CurrencyRate) {
        if (!inputTextFieldState.value.isNumeric) {
            showErrorDialog("Number is invalid.")
            return
        }
        _inputTextFieldState.value = inputTextFieldState.value.copy(
            value = (inputTextFieldState.value.value.toDouble() / this.rate.value.value * rate.value).toString()
        )
        _rate.value = rate as Rate
    }

    private fun getRatesPreferences() {
        getPreferencesJob?.cancel()
        getPreferencesJob = preferencesUseCases.getRatesPreferencesDataStoreFlow()
            .catch { exception ->
                exception.printStackTrace()
            }
            .onEach { preferences ->
                _lastFetchTimestamp.value = preferences.lastFetchTimestamp
            }.launchIn(viewModelScope)
    }

    private fun getLastFetchTimeStamp() {
        var getLastFetchTimeStampJob: Job? = null
        getLastFetchTimeStampJob = preferencesUseCases.getRatesPreferencesDataStoreFlow()
            .catch { exception ->
                exception.printStackTrace()
            }
            .cancellable()
            .onEach { preferences ->
                _lastFetchTimestamp.value = preferences.lastFetchTimestamp
                getRates()
                getLastFetchTimeStampJob?.cancel()
            }.launchIn(viewModelScope)
    }


    private suspend fun getRates() {
        val nowTime = System.currentTimeMillis() / 1000
        if (nowTime - lastFetchTimestamp.value <= 60 * 30) { return }
        getRatesJob?.cancel()
        getRatesJob = ratesApiUseCases.getRates(appId = appId, base = base)
            .catch { exception ->
                exception.message?.let { showErrorDialog(it) }
                exception.printStackTrace()
            }
            .onEach { baseResult ->
                when (baseResult) {
                    is BaseResult.Success -> {
                        val newList: MutableList<Rate> = mutableListOf()
                        baseResult.data.forEach { newList.add(Rate(it.currency, it.value)) }
                        _rates.value = newList
                        ratesPersistUseCases.insertRatesPersist(newList)
                        preferencesUseCases.updateLastFetchTimestamp(nowTime)
                    }
                    is BaseResult.Error -> {
                        showErrorDialog(baseResult.rawResponse.name)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun showErrorDialog(content: String) {
        _errorDialogState.value = errorDialogState.value.copy(
            isShow = true,
            content = content
        )
    }

    private suspend fun getPersistRates() {
        ratesPersistUseCases.getRatesPersistFlow()
            .catch { e ->
                e.printStackTrace()
            }
            .collect {
                if (rates.value.isEmpty()) {
                    _rates.value = it
                }
            }
    }
}