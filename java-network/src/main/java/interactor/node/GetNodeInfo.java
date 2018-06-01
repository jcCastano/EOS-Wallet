package interactor.node;

import interactor.UseCase;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import model.NodeInfo;
import services.NodeAPI;


/**
 * Created by jc on 5/31/18.
 */
public class GetNodeInfo extends UseCase<NodeInfo> {

    private NodeAPI service;

    public GetNodeInfo(NodeAPI service) {
        this.service = service;
    }

    @Override
    public Observable<NodeInfo> buildObservable() {
        return service.getInfo();
    }

    public void execute(DisposableObserver<NodeInfo> observer) {
        execute(observer, Schedulers.io());
    }

}
