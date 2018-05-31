package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by jc on 5/25/18.
 */
public interface WalletAPI {

    @GET("wallet/list_wallets")
    Call<String[]> getList();

    @POST("wallet/unlock")
    Call<JsonObject> unlock(@Body JsonArray accountAndPassword);

}
