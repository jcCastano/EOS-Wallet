package com.eosio.interactor.wallet;

import com.eosio.interactor.UseCaseWithParams;
import com.eosio.services.WalletAPI;
import com.google.gson.JsonObject;
import io.reactivex.Observable;

/**
 * Created by jc on 6/5/18.
 */
public class LockWallet extends UseCaseWithParams<JsonObject, String> {

    private WalletAPI service;

    public LockWallet(WalletAPI service) {
        this.service = service;
    }


    @Override
    public Observable<JsonObject> buildObservable(String walletName) {
        return service.lock(walletName);
    }

}
