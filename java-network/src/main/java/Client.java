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

}
