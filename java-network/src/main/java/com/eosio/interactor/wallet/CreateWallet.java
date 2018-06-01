package interactor.wallet;

import interactor.UseCaseWithParams;
import io.reactivex.Observable;
import services.WalletAPI;

/**
 * Created by jc on 6/1/18.
 */
public class CreateWallet extends UseCaseWithParams<String, String> {

    private WalletAPI service;

    public CreateWallet(WalletAPI service) {
        this.service = service;
    }

    @Override
    public Observable<String> buildObservable(String walletName) {
        return service.create(walletName);
    }

}
