package com.eosio.interactor.error;

import com.jc.shared.tasks.Task;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by jc on 6/5/18.
 */
public class ErrorListener {

    private JLabel errorLabel;

    public ErrorListener(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }

    public void setError(String error) {
        if (errorLabel != null)
            errorLabel.setText(error);
        Runnable clearError = new Runnable() {
            @Override
            public void run() {
                clear();
            }
        };
        Task.executeWithDelay(clearError, 10, TimeUnit.SECONDS);
    }

    public synchronized void clear() {
        if (!errorLabel.getText().isEmpty())
            errorLabel.setText("");
    }

}
