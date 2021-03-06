package com.eosio.interactor.wallet;

import com.eosio.RetrofitFactory;
import com.eosio.interactor.error.ErrorListener;
import com.eosio.services.WalletAPI;
import com.google.gson.JsonObject;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Retrofit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jc on 6/5/18.
 */
public class LockButtonAction implements ActionListener {

    private LockWallet lockWallet;
    private JTextField walletName;
    private RefreshWalletListAction refreshWallets;
    private JList<String> walletList;
    private ErrorListener errorListener;

    public LockButtonAction(JTextField walletName, RefreshWalletListAction refreshWallets, JList<String> walletList, ErrorListener errorListener, WalletAPI walletAPI) {
        lockWallet = new LockWallet(walletAPI);
        this.walletName = walletName;
        this.refreshWallets = refreshWallets;
        this.walletList = walletList;
        this.errorListener = errorListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (walletList.getSelectedValue() != null) {
            errorListener.clear();
            String walletName = walletList.getSelectedValue().replaceAll("(\\s|\\*)", "");
            lockWallet.buildObservable(walletName)
                    .subscribeWith(new DisposableObserver<JsonObject>() {
                        @Override
                        public void onNext(JsonObject jsonObject) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            refreshWallets.execute();
                        }
                    });
        } else
            errorListener.setError("No wallet selected");
    }

}
