package com.eosio.interactor.error;

import com.jc.shared.tasks.Task;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by jc on 6/5/18.
 */
public class WalletListError {

    private JLabel walletErrorLabel;
    private static WalletListError sharedInstance;
    private final Object clear = new Object();

    private WalletListError() {
    }

    public static WalletListError getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new WalletListError();
        }
        return sharedInstance;
    }

    public void setWalletErrorLabel(JLabel walletErrorLabel) {
        this.walletErrorLabel = walletErrorLabel;
    }

    public void setError(String error) {
        if (walletErrorLabel != null)
            walletErrorLabel.setText(error);
        Runnable clearError = new Runnable() {
            @Override
            public void run() {
                clear();
            }
        };
        Task.executeWithDelay(clearError, 10, TimeUnit.SECONDS);
    }

    public void clear() {

        synchronized (clear) {
            if (!walletErrorLabel.getText().isEmpty())
                walletErrorLabel.setText("");
        }

    }

}
