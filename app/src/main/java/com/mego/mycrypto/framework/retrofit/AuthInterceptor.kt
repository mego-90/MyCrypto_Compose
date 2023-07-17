package com.mego.mycrypto.framework.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("X-RapidAPI-Host","coingecko.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "5850ebe98amshad2d19a225aa14dp1221ffjsne1c3b7a57a75")
            .build()
        return chain.proceed(request)
    }
}