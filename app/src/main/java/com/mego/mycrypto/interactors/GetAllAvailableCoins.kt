package com.mego.mycrypto.interactors

import com.mego.mycrypto.data.CoinRepository
import com.mego.mycrypto.domain.Coin

class GetAllAvailableCoins (private val coinsRepository: CoinRepository){

    suspend operator fun invoke() =
        coinsRepository.getAllAvailableCoins()

}