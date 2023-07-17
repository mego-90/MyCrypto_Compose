package com.mego.mycrypto.data

import android.util.Log
import com.mego.mycrypto.domain.Coin
import com.mego.mycrypto.framework.connectivity.MyInternetConnectivityChecker
import com.mego.mycrypto.framework.retrofit.CoinRetrofitDatasource
import com.mego.mycrypto.framework.room.CoinRoomDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoinRepository ( private val myInternetConnectivityChecker: MyInternetConnectivityChecker,
                       private val roomDatasource: CoinRoomDatasource,
                       private val retrofitDatasource: CoinRetrofitDatasource) {

    suspend fun fetchCoinData(coinID:String) : Flow<Coin> {
        if (myInternetConnectivityChecker.isConnected)
            return retrofitDatasource.fetchCoinData(coinID)
        else
            return roomDatasource.fetchCoinData(coinID)
    }

    suspend fun updateLastPrice(coinID: String, priceCurrency:String) = retrofitDatasource.updateLastPrice(coinID, priceCurrency)

    suspend fun getAllAvailableCoins() : Flow<List<Coin>> {
        return if (myInternetConnectivityChecker.isConnected)
            retrofitDatasource.getAllAvailableCoins() else
                flow { emit (emptyList<Coin>() ) }

    }

    suspend fun getFavCoins() =
        roomDatasource.getFavCoins()

    suspend fun addCoinToFav(coin: Coin) = roomDatasource.addCoinToFav(coin)

}