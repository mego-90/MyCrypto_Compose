package com.mego.mycrypto.app

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mego.mycrypto.data.CoinDataSource
import com.mego.mycrypto.data.CoinRepository
import com.mego.mycrypto.framework.connectivity.MyInternetConnectivityChecker
import com.mego.mycrypto.framework.retrofit.AuthInterceptor
import com.mego.mycrypto.framework.retrofit.CoinRetrofitDatasource
import com.mego.mycrypto.framework.retrofit.CoinsRemoteService
import com.mego.mycrypto.framework.room.CoinRoomDatasource
import com.mego.mycrypto.framework.room.CoinsDatabase
import com.mego.mycrypto.interactors.*
import com.mego.mycrypto.ui.CoinsListViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val mainModule = module {
            single { MyInternetConnectivityChecker(this@MyApplication) }

            single { provideRetrofit() }
            single { provideCoinRemoteService( get() ) }

            single { provideDAO() }

            factory { CoinRetrofitDatasource( get() ) }
            factory { CoinRoomDatasource( get() )}
            factory { CoinRepository( get(), get(), get()) }

            factory { AddCoinToFav( get() ) }
            factory { FetchCoinData( get() ) }
            factory { GetAllAvailableCoins( get() ) }
            factory { GetFavCoins( get() ) }
            factory { UpdateLastPrice ( get() ) }

            viewModel { CoinsListViewModel( get(), get(), get(), get() ) }

        }

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(mainModule)
        }

    }


    private fun provideRetrofit() : Retrofit {
        val myClient = OkHttpClient().newBuilder()
            .addInterceptor(AuthInterceptor())
            .build()

        return Retrofit.Builder()
            .client(myClient)
            .baseUrl("https://coingecko.p.rapidapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun provideCoinRemoteService(retrofit: Retrofit) =
        retrofit.create( CoinsRemoteService::class.java )

    private fun provideDAO () =
        Room
            .databaseBuilder(this@MyApplication, CoinsDatabase::class.java, CoinsDatabase.name)
            .build()
            .coinsDAO


}