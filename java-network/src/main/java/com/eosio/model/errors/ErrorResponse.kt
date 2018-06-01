package com.eosio.model.errors

import com.google.gson.JsonObject

/**
 * Created by jc on 5/31/18.
 */
class ErrorResponse {

    var code: Int = 0
        internal set
    var message: String? = null
        internal set
    var error: JsonObject? = null
        internal set
}
