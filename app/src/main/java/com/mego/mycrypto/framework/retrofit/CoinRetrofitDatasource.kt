package com.mego.mycrypto.framework.retrofit

import com.mego.mycrypto.data.CoinDataSource
import com.mego.mycrypto.domain.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single

class CoinRetrofitDatasource (private val coinService: CoinsRemoteService) : CoinDataSource {

    override suspend fun fetchCoinData(coinID: String): Flow<Coin> =
        flowOf( coinService.fetchCoinData(coinID) )

    override suspend fun updateLastPrice(coinID: String, priceCurrency: String): Flow<Coin> =
        TODO("Not yet implemented")

    suspend fun getAllAvailableCoins(): Flow<List<Coin>> =
        flow{ emit ( coinService.getAllCoinsList() ) }

    override suspend fun getFavCoins(): Flow<List<Coin>> {
        val favCoins = ArrayList<Coin>()
        listOf("bitcoin","ethereum","dogecoin").forEach{
            favCoins.add( fetchCoinData(it).single() )
        }
        return flow { emit(favCoins) }
    }

}