package com.eosio.interactor.wallet;

import com.eosio.RetrofitFactory;
import com.eosio.interactor.error.ErrorListener;
import com.eosio.services.WalletAPI;
import retrofit2.Retrofit;

import javax.swing.*;

/**
 * Created by jc on 6/13/18.
 */
public class CreateWalletActionFactory {

    private WalletAPI walletAPI;

    public CreateWalletActionFactory(String url) {
        Retrofit walletClient = RetrofitFactory.create(url);
        this.walletAPI = walletClient.create(WalletAPI.class);
    }

    public CreateButtonAction makeCreateWalletAction(JTextField walletName, JTextField walletPassword, JLabel prompt, ErrorListener errorListener) {
        return new CreateButtonAction(walletName, walletPassword, prompt, errorListener, walletAPI);
    }

}
