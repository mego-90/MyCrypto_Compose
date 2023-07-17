package com.mego.mycrypto.framework.room

import android.util.Log
import com.mego.mycrypto.data.CoinDataSource
import com.mego.mycrypto.domain.Coin
import kotlinx.coroutines.flow.Flow

class CoinRoomDatasource (private val coinsDAO: CoinsDAO) : CoinDataSource {

    override suspend fun fetchCoinData(coinID: String): Flow<Coin> =
        coinsDAO.getCoinById(coinID)

    override suspend fun updateLastPrice(coinID: String, priceCurrency: String): Flow<Coin> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavCoins(): Flow<List<Coin>> =
        coinsDAO.getAllFavCoins()

    suspend fun addCoinToFav( coin: Coin ) =
        coinsDAO.insertFavCoin(coin)

}