package com.eosio.interactor.wallet;

import com.eosio.RetrofitFactory;
import com.eosio.interactor.error.ErrorListener;
import com.eosio.model.errors.ErrorResponse;
import com.eosio.services.WalletAPI;
import com.google.gson.Gson;
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
public class CreateButtonAction implements ActionListener {

    private CreateWallet createWallet;
    private JTextField walletName;
    private JTextField walletPassword;
    private ErrorListener errorListener;
    private JLabel prompt;

    public CreateButtonAction(JTextField walletName, JTextField walletPassword, JLabel prompt, ErrorListener errorListener, WalletAPI walletAPI) {
        this.walletName = walletName;
        this.walletPassword = walletPassword;
        this.prompt = prompt;
        this.errorListener = errorListener;
        createWallet = new CreateWallet(walletAPI);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        create();
    }

    private void create() {
        createWallet.buildObservable(walletName.getText())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String password) {
                        prompt.setText("Save password to safe place");
                        walletPassword.setText(password);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            Gson gson = new Gson();
                            try {
                                ErrorResponse errorResponse = gson.fromJson(exception.response().errorBody().string(), ErrorResponse.class);
                                System.out.println("Error response: " + errorResponse.getCode());
                                if (errorResponse.getError() != null)
                                    errorListener.setError(errorResponse.getError().what);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
