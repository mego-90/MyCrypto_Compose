package com.mego.mycrypto.interactors

import com.mego.mycrypto.data.CoinRepository

class FetchCoinData (private val coinRepository: CoinRepository) {

    suspend operator fun invoke(coinID:String) = coinRepository.fetchCoinData(coinID)

}