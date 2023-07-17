package com.mego.mycrypto.data

import com.mego.mycrypto.domain.Coin
import kotlinx.coroutines.flow.Flow

interface CoinDataSource {

    suspend fun fetchCoinData( coinID:String ) : Flow<Coin>

    suspend fun updateLastPrice( coinID:String, priceCurrency:String ) : Flow<Coin>

    suspend fun getFavCoins() : Flow<List<Coin>>

}