package com.eosio.model.errors;

import com.google.gson.JsonArray;

/**
 * Created by jc on 6/5/18.
 */
public class EOSError {

    public long code;
    public String name;
    public String what;
    public JsonArray details;

}
