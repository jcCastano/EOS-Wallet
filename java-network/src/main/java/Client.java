import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.errors.ErrorResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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
        retrofit = RetrofitFactory.create("http://localhost:"+ port + "/v1/", Date.class, new EosUtcAdapter());
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
                    System.out.println("Error response: " + errorResponse.getError());
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
