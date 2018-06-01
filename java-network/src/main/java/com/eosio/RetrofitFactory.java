import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;

/**
 * Created by jc on 5/31/18.
 */
public class RetrofitFactory {

    private String url;

    public RetrofitFactory(String url) {
        this.url = url;
    }

    private Retrofit create(Gson gson) {
        return create(url, gson);
    }

    public static Retrofit create(String url, Gson gson) {
        Retrofit retrofit = createBuilder(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    public static Retrofit create(String url) {
        return createBuilder(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit create(Type type, Object typeAdapter) {
        return create(url, type, typeAdapter);
    }

    public static Retrofit create(String url, Type type, Object typeAdapter) {
        Gson gson = new GsonBuilder().registerTypeAdapter(type, typeAdapter).create();
        return create(url, gson);
    }

    private static Retrofit.Builder createBuilder(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

}
