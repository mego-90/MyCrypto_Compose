package com.mego.mycrypto.framework.retrofit

import com.mego.mycrypto.domain.Coin
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinsRemoteService {

    @GET("coins/{id}")
    suspend fun fetchCoinData( @Path("id") coinID:String) : Coin

    @GET("simple/price")
    suspend fun updateLastPrice( @Query("ids") coinID: String, @Query("vs_currencies") priceCurrency: String): Coin

    @GET("coins/list")
    suspend fun getAllCoinsList(): List<Coin>

}