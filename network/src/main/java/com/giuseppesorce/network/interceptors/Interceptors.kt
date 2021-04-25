package com.giuseppesorce.network.interceptors

import com.giuseppesorce.network.data.NetworkEnvironment
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Giuseppe Sorce
 */

@Singleton
class AuthHeaderInterceptor @Inject
constructor(var environment: NetworkEnvironment) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val builder = request.newBuilder()
        if (environment.authorization.isNotEmpty()) {
         //   builder.addHeader("Authorization", "Bearer ${environment.authorization}")
        }
        return chain.proceed(builder.build())
    }
}

@Singleton
class DynamicHostInterceptor @Inject
constructor(var environment: NetworkEnvironment) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var host = environment.base_url.toHttpUrlOrNull()?.host ?: ""
        val newUrl = request.url.newBuilder()
            .host(host)
            .build()
        request = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(request)
    }
}