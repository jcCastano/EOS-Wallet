import com.eosio.RetrofitFactory;
import retrofit2.Retrofit;
import com.eosio.util.adapter.EosUtcAdapter;

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
