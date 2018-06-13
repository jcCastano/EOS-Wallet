package com.eosio;

import com.eosio.interactor.error.ErrorListener;
import com.eosio.interactor.node.GetNodeInfo;
import com.eosio.interactor.wallet.*;
import com.eosio.services.NodeAPI;
import com.eosio.model.NodeInfo;
import com.eosio.util.adapter.EosUtcAdapter;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by jc on 6/1/18.
 */
public class MainFormPanel {

    private static JFrame frame;
    private JLabel serverVersionValue;
    private JPanel mainFormJPanel;
    private JTabbedPane mainTabPanel;
    private JPanel NodeInfoPanel;
    private JButton createButton;
    private JButton unlockButton;
    private JButton lockButton;
    private JTextField walletName;
    private JPasswordField walletPassword;
    private JLabel walletLabel;
    private JLabel passwordLabel;
    private JButton refreshWalletsButton;
    private JList<String> walletList;
    private JLabel walletErrorLabel;
    private JPanel createWallet;
    private JPanel wallets;
    private JTextField walletNameToCreate;
    private JLabel createWalletNameLabel;
    private JTextField createdWalletPassword;
    private JLabel savePasswordLabel;
    private JLabel createWalletError;
    private RefreshWalletListAction refreshWalletListListener;

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setContentPane(new MainFormPanel().mainFormJPanel);
        frame.setBounds(100,100, 450, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    MainFormPanel() {

        ErrorListener walletsErrorListener = new ErrorListener(walletErrorLabel);

        String url = "http://185.109.149.236:"+ 8888 + "/v1/";
        WalletsActionFactory walletsActionFactory = new WalletsActionFactory(url, walletList, walletsErrorListener);
        CreateWalletActionFactory createWalletActionFactory = new CreateWalletActionFactory(url);

        refreshWalletListListener = walletsActionFactory.makeRefreshWalletsAction();
        refreshWalletsButton.addActionListener(refreshWalletListListener);
        unlockButton.addActionListener(walletsActionFactory.makeUnlockAction(walletName, walletPassword, refreshWalletListListener));
        lockButton.addActionListener(walletsActionFactory.makeLockWalletAction(walletName, refreshWalletListListener));
        createButton.addActionListener(createWalletActionFactory.makeCreateWalletAction(walletNameToCreate, createdWalletPassword, savePasswordLabel, new ErrorListener(createWalletError)));

        createdWalletPassword.setBorder(null);
        createdWalletPassword.setDisabledTextColor(Color.black);
        createdWalletPassword.setBackground(null);

        Retrofit nodeClient = RetrofitFactory.create(url, Date.class, new EosUtcAdapter());
        GetNodeInfo getNodeInfo = new GetNodeInfo(nodeClient.create(NodeAPI.class));
        getNodeInfo.buildObservable().subscribe(new Consumer<NodeInfo>() {
            @Override
            public void accept(NodeInfo nodeInfo) throws Exception {
                System.out.println();
                System.out.println("Finally: " + nodeInfo.getHeadBlockTime().getTime());
                System.out.println("Finally: " + nodeInfo.getHeadBlockTime());
                serverVersionValue.setText(nodeInfo.getServerVersion());
            }
        });

        mainTabPanel.addFocusListener(refreshWalletListListener);
    }
}
