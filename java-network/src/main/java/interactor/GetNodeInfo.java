package interactor;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import model.ChainInfo;
import services.ChainAPI;


/**
 * Created by jc on 5/31/18.
 */
public class GetNodeInfo extends UseCase<ChainInfo> {

    private ChainAPI service;

    public GetNodeInfo(ChainAPI service) {
        this.service = service;
    }

    @Override
    public Observable<ChainInfo> buildUseCaseObservable() {
        return service.getInfo();
    }

    public void execute(DisposableObserver<ChainInfo> observer) {
        execute(observer, Schedulers.io());
    }

    public Observable<ChainInfo> observable() {
        return buildUseCaseObservable();
    }

}
