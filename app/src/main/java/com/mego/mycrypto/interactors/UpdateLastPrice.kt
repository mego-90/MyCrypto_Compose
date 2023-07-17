package com.mego.mycrypto.interactors

import com.mego.mycrypto.data.CoinRepository

class UpdateLastPrice (private val coinRepository: CoinRepository){

    suspend operator fun invoke (coinID:String, priceCurrency:String) =
        coinRepository.updateLastPrice(coinID, priceCurrency)

}