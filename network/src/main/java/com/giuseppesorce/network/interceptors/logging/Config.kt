package com.giuseppesorce.network.interceptors.logging

import android.util.Log
import cn.netdiscovery.http.interceptor.log.LogManager
import cn.netdiscovery.http.interceptor.log.LogProxy


fun init() {

    LogManager.logProxy(object : LogProxy {
        override fun e(tag: String, msg: String) {
            Log.e(tag,msg)
        }

        override fun w(tag: String, msg: String) {
            Log.w(tag,msg)
        }

        override fun i(tag: String, msg: String) {
            Log.i(tag,msg)
        }

        override fun d(tag: String, msg: String) {
            Log.d(tag,msg)
        }
    })
}