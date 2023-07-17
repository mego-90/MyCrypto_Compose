package com.mego.mycrypto.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@Entity
@JsonClass(generateAdapter = true)
data class Coin (
    @PrimaryKey
    val id:String ,
    val name:String ,
    val symbol:String
    ) : Serializable {
    @Embedded
    var image:CoinImage? = null

    @Embedded
    @Json(name = "market_data")
    var marketData: MarketData? = null

    constructor(id:String , name:String , symbol:String, image: CoinImage, marketData: MarketData)
            : this( id, name, symbol ) {
        this.image = image
        this.marketData = marketData
    }

    }

@Entity
@JsonClass(generateAdapter = true)
data class CoinImage (
    @Json(name = "thumb")
    val thumbUrl : String,
    @Json(name = "small")
    val smallUrl : String,
    @Json(name = "large")
    val largeUrl : String
        ) : Serializable

@Entity
@JsonClass(generateAdapter = true)
data class MarketData (
    @Embedded
    @Json(name = "current_price")
    val currentPrice: LastPrice,
    val price_change_percentage_24h : Float?
        ) : Serializable


@Entity
@JsonClass(generateAdapter = true)
data class LastPrice(
    @Json(name = "usd")
    val dollar : Float,
    @Json(name = "eur")
    val euro : Float
) : Serializable