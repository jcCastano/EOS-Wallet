import com.eosio.RetrofitFactory;
import com.google.gson.Gson;
import com.eosio.interactor.node.GetNodeInfo;
import com.eosio.interactor.wallet.CreateWallet;
import com.eosio.interactor.wallet.GetWallets;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import com.eosio.model.NodeInfo;
import com.eosio.model.errors.ErrorResponse;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import com.eosio.services.NodeAPI;
import com.eosio.services.WalletAPI;
import com.eosio.util.adapter.EosUtcAdapter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by jc on 5/24/18.
 */
public class Main {

    public static void main(String[] args) {
        Retrofit nodeClient = RetrofitFactory.create("http://localhost:"+ 8888 + "/v1/", Date.class, new EosUtcAdapter());
        GetNodeInfo getNodeInfo = new GetNodeInfo(nodeClient.create(NodeAPI.class));
        getNodeInfo.buildObservable().subscribe(new Consumer<NodeInfo>() {
            @Override
            public void accept(NodeInfo nodeInfo) throws Exception {
                System.out.println();
                System.out.println("Finally: " + nodeInfo.getHeadBlockTime().getTime());
                System.out.println("Finally: " + nodeInfo.getHeadBlockTime());
            }
        });

        Retrofit walletClient = RetrofitFactory.create("http://localhost:"+ 9999 + "/v1/");
        WalletAPI walletAPI = walletClient.create(WalletAPI.class);

        GetWallets wallets = new GetWallets(walletAPI);
        wallets.buildObservable().subscribe(new Consumer<String[]>() {
            @Override
            public void accept(String[] strings) throws Exception {
                System.out.println();
                for (String wallet : strings) {
                    System.out.println("Found wallet: " + wallet);
                }
            }
        });

        CreateWallet createWallet = new CreateWallet(walletAPI);
        createWallet.buildObservable("default").subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String password) {
                System.out.println();
                System.out.println("Wallet password: " + password);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println();
                System.out.println("Error response: " + e.getMessage());
                if (e instanceof HttpException) {
                    HttpException exception = (HttpException) e;
                    Gson gson = new Gson();
                    try {
                        ErrorResponse errorResponse = gson.fromJson(exception.response().errorBody().string(), ErrorResponse.class);
                        System.out.println("Error response code: " + errorResponse.getCode());
                        if (errorResponse.getError() != null)
                            System.out.println("Error response what: " + errorResponse.getError().what);
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
