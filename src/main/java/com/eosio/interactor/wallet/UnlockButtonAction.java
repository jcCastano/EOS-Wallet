package com.eosio.interactor.wallet;

import com.eosio.RetrofitFactory;
import com.eosio.interactor.error.WalletListError;
import com.eosio.model.errors.ErrorResponse;
import com.eosio.services.WalletAPI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;
import retrofit2.Retrofit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by jc on 6/5/18.
 */
public class UnlockButtonAction implements ActionListener {

    private UnlockWallet unlockWallet;
    private JTextField walletName;
    private JPasswordField password;
    private RefreshWalletListAction refreshWallets;

    public UnlockButtonAction(JTextField walletName, JPasswordField password, RefreshWalletListAction refreshWallets) {
        this.walletName = walletName;
        this.password = password;
        this.refreshWallets = refreshWallets;
        String url = "http://localhost:"+ 9999 + "/v1/";
        Retrofit walletClient = RetrofitFactory.create(url);
        WalletAPI walletAPI = walletClient.create(WalletAPI.class);
        unlockWallet = new UnlockWallet(walletAPI);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String name = walletName.getText();
        String passwordStr = new String(password.getPassword());
        unlockWallet.buildObservable(name, passwordStr)
                .subscribeWith(new DisposableObserver<JsonObject>() {
                    @Override
                    public void onNext(JsonObject jsonObject) {
                        System.out.println("Got unlock next: " + jsonObject.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Got unlock error: " + e.getMessage());
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            Gson gson = new Gson();
                            try {
                                ErrorResponse errorResponse = gson.fromJson(exception.response().errorBody().string(), ErrorResponse.class);
                                System.out.println("Error response: " + errorResponse.getCode());
                                if (errorResponse.getError() != null)
                                    WalletListError.getSharedInstance().setError(errorResponse.getError().what);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        refreshWallets.execute();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Unlock complete");
                        refreshWallets.execute();
                    }
                });
        password.setText("");
    }

}
