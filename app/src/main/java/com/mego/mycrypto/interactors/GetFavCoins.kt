package com.mego.mycrypto.interactors

import com.mego.mycrypto.data.CoinRepository

class GetFavCoins (private val coinRepository: CoinRepository) {

    suspend operator fun invoke () =
        coinRepository.getFavCoins()

}