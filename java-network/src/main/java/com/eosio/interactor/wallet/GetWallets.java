package com.eosio.interactor.wallet;

import com.eosio.interactor.UseCase;
import io.reactivex.Observable;
import com.eosio.services.WalletAPI;

/**
 * Created by jc on 5/31/18.
 */
public class GetWallets extends UseCase<String[]> {

    private WalletAPI service;

    public GetWallets(WalletAPI service) {
        this.service = service;
    }

    @Override
    public Observable<String[]> buildObservable() {
        return service.getList();
    }

}
