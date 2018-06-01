package services;

import io.reactivex.Observable;
import model.ChainInfo;
import retrofit2.http.GET;


/**
 * Created by jc on 5/24/18.
 */
public interface ChainAPI {

    @GET("chain/get_info")
    Observable<ChainInfo> getInfo();

}
