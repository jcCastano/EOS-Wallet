package interactor;

import io.reactivex.Observable;
import services.WalletAPI;

/**
 * Created by jc on 5/31/18.
 */
public class GetWallets extends UseCase<String[]> {

    private WalletAPI service;

    public GetWallets(WalletAPI service) {
        this.service = service;
    }

    @Override
    public Observable<String[]> buildObservable() {
        return service.getList();
    }

}
