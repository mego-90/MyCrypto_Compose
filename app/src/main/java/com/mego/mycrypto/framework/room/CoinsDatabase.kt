package com.mego.mycrypto.framework.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mego.mycrypto.domain.Coin

@Database (entities = [Coin::class], version = 1)
abstract class CoinsDatabase : RoomDatabase() {

    abstract val coinsDAO : CoinsDAO

    companion object {
        const val name = "CoinsDatabase"
    }

}