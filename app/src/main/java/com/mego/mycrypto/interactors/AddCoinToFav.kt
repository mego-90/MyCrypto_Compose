package com.mego.mycrypto.interactors

import android.util.Log
import com.mego.mycrypto.data.CoinRepository
import com.mego.mycrypto.domain.Coin

class AddCoinToFav (private val coinRepository: CoinRepository) {

    suspend operator fun invoke (coin : Coin) =
        coinRepository.addCoinToFav(coin)

}