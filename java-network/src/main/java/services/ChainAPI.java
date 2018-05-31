package services;

import model.ChainInfo;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jc on 5/24/18.
 */
public interface ChainAPI {

    @GET("chain/get_info")
    Call<ChainInfo> getInfo();

}
