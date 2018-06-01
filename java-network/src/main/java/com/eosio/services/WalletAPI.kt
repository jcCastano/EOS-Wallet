package com.eosio.services

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by jc on 5/25/18.
 */
interface WalletAPI {

    @get:GET("wallet/list_wallets")
    val list: Observable<Array<String>>

    @POST("wallet/create")
    fun create(@Body walletName: String): Observable<String>

    @POST("wallet/unlock")
    fun unlock(@Body accountAndPassword: JsonArray): Observable<JsonObject>

}
