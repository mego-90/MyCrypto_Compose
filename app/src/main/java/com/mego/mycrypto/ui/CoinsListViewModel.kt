package com.mego.mycrypto.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mego.mycrypto.domain.Coin
import com.mego.mycrypto.interactors.AddCoinToFav
import com.mego.mycrypto.interactors.FetchCoinData
import com.mego.mycrypto.interactors.GetAllAvailableCoins
import com.mego.mycrypto.interactors.GetFavCoins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class CoinsListViewModel (

    private val fetchCoinData: FetchCoinData,
    private val getAllAvailableCoins: GetAllAvailableCoins,
    private val addCoinToFav: AddCoinToFav,
    private val getFavCoins: GetFavCoins ) : ViewModel() {

    val coinsList = mutableStateListOf<Coin>()
    private val allAvailableCoins = ArrayList<Coin>()
    val autocompleteFilteredList = mutableStateListOf<Coin>()

    private var autocompleteFilteringJob : Job? = null

    init {
        updateCoinData()
        loadAllAvailableCoins()
    }

    private fun updateCoinData() {
        viewModelScope.launch () {
            coinsList.addAll( getFavCoins().first() )
        }
    }

    private fun loadAllAvailableCoins() {
        viewModelScope.launch {
            allAvailableCoins.addAll( getAllAvailableCoins().single() )
        }
    }

    fun updateCoinsAutocompleteList(input : String ) {
        val cancellingJob = viewModelScope.launch {
            autocompleteFilteringJob?.cancel()
        }

        if (input.isEmpty()) {
            autocompleteFilteredList.clear()
            //_autocompleteFilteredFlow.value = emptyList()
            return
        }
        autocompleteFilteringJob = viewModelScope.launch {
            cancellingJob.join()
            autocompleteFilteredList.clear()
            autocompleteFilteredList.addAll( allAvailableCoins.asFlow()
                .filter { coin ->
                    coin.name.contains(input, ignoreCase = true)
                }.take(5)
                .toList() )
        }
    }

    fun clearAutocompleteList() {
        autocompleteFilteredList.clear()
    }

    fun addToMyFavCoin( coinID:String ) {
        viewModelScope.launch(Dispatchers.IO) {
            val coin = fetchCoinData(coinID).first()
            if (coinsList.contains(coin)) return@launch
            addCoinToFav(coin)
            coinsList.add(coin)

        }
    }
}