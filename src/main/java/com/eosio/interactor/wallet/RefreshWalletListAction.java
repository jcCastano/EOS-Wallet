package com.eosio.interactor.wallet;

import com.eosio.RetrofitFactory;
import com.eosio.services.WalletAPI;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Retrofit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Created by jc on 6/5/18.
 */
public class RefreshWalletListAction extends FocusAdapter implements ActionListener {

    private GetWallets wallets;
    private JList<String> walletList;

    public RefreshWalletListAction(JList<String> walletList, WalletAPI walletAPI) {
        this.walletList = walletList;
        wallets = new GetWallets(walletAPI);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        execute();
    }

    public void execute() {
        wallets.buildObservable()
                .subscribeWith(new DisposableObserver<String[]>() {
                    @Override
                    public void onNext(String[] foundWallets) {
                        if (foundWallets.length > 0)
                            walletList.setListData(foundWallets);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void focusGained(FocusEvent e) {
        super.focusGained(e);
        execute();
    }
}
