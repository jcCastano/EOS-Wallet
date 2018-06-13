package com.eosio.interactor.wallet;

import com.eosio.RetrofitFactory;
import com.eosio.interactor.error.ErrorListener;
import com.eosio.services.WalletAPI;
import retrofit2.Retrofit;

import javax.swing.*;

/**
 * Created by jc on 6/12/18.
 */
public class WalletsActionFactory {

    private WalletAPI walletAPI;
    private ErrorListener errorListener;
    private JList<String> walletList;

    public WalletsActionFactory(String url, JList<String> walletList, ErrorListener errorListener) {
        Retrofit walletClient = RetrofitFactory.create(url);
        this.walletAPI = walletClient.create(WalletAPI.class);
        this.walletList = walletList;
        this.errorListener = errorListener;
    }

    public RefreshWalletListAction makeRefreshWalletsAction() {
        return new RefreshWalletListAction(walletList, walletAPI);
    }

    public UnlockButtonAction makeUnlockAction(JTextField walletName, JPasswordField password, RefreshWalletListAction refreshWallets) {
        return new UnlockButtonAction(walletName, password, refreshWallets, errorListener, walletAPI);
    }

    public LockButtonAction makeLockWalletAction(JTextField walletName, RefreshWalletListAction refreshWallets) {
        return new LockButtonAction(walletName, refreshWallets, walletList, errorListener, walletAPI);
    }

}
