package services;

import io.reactivex.Observable;
import model.NodeInfo;
import retrofit2.http.GET;


/**
 * Created by jc on 5/24/18.
 */
public interface NodeAPI {

    @GET("chain/get_info")
    Observable<NodeInfo> getInfo();

}
