package interactor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import model.errors.ErrorResponse;
import services.WalletAPI;

/**
 * Created by jc on 5/31/18.
 */
public class UnlockWallet extends UseCaseWithParams<JsonObject, JsonArray> {

    private WalletAPI service;

    public UnlockWallet(WalletAPI service) {
        this.service = service;
    }

    public Observable<JsonObject> buildObservable(String account, String password) {
        JsonArray accountAndPassword = new JsonArray();
        accountAndPassword.add(account);
        accountAndPassword.add(password);
        return buildObservable(accountAndPassword);
    }

    @Override
    public Observable<JsonObject> buildObservable(JsonArray accountAndPassword) {
        return service.unlock(accountAndPassword);
    }

}
