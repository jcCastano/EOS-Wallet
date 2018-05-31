import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.ChainInfo;
import model.Wallets;
import model.errors.ErrorResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.ChainAPI;
import services.WalletAPI;
import util.adapter.EosUtcAdapter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by jc on 5/24/18.
 */
public class Client {

    private Retrofit retrofit;

    public Client(int port) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new EosUtcAdapter()).create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:"+ port + "/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public void getInfo() {
        ChainAPI service = retrofit.create(ChainAPI.class);
        Call<ChainInfo> repos = service.getInfo();
        repos.enqueue(new Callback<ChainInfo>() {
            @Override
            public void onResponse(Call<ChainInfo> call, Response<ChainInfo> response) {
                System.out.println();
                System.out.println("Finally: " + response.body().getHeadBlockTime().getTime());
                System.out.println("Finally: " + response.body().getHeadBlockTime());
                System.out.println();
            }

            @Override
            public void onFailure(Call<ChainInfo> call, Throwable t) {
                System.out.println("Get Error: " + t.getMessage());
            }
        });
    }

    public void getWalletList() {
        WalletAPI service = retrofit.create(WalletAPI.class);
        final Call<String[]> repos = service.getList();
        repos.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                System.out.println();


                if (response.body().length > 0) {
                    for (String wallet : response.body()) {
                        System.out.println("Finally: " + wallet);
                    }
                } else
                    System.out.println("No wallets found");
                System.out.println();
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                System.out.println();
                System.out.println("Get Error: " + t.getMessage());
                System.out.println();
            }
        });
    }

    public void unlockWallet(JsonArray accountAndPassword) {
        WalletAPI service = retrofit.create(WalletAPI.class);
        final Call<JsonObject> repos = service.unlock(accountAndPassword);
        repos.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                System.out.println();
                System.out.println("Got response from unlock");
                try {
                    String errorBody = response.errorBody().string();
                    System.out.println("Error body: " + errorBody);
                    Gson gson = new Gson();
                    ErrorResponse errorResponse = gson.fromJson(errorBody, ErrorResponse.class);
                    System.out.println("Error response: " + errorResponse.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println();
                System.out.println("Get Error: " + t.getMessage());
                System.out.println();
            }
        });
    }

}
