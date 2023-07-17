package com.mego.mycrypto.framework.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mego.mycrypto.domain.Coin
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinsDAO {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavCoin(coin: Coin)

    @Query("SELECT * FROM Coin Where id LIKE :id")
    fun getCoinById( id:String) : Flow<Coin>

    @Query("SELECT * FROM Coin")
    fun getAllFavCoins() : Flow<List<Coin>>

}