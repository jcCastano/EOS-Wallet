package interactor;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jc on 5/31/18.
 */
abstract class UseCaseWithParams<T, Params> extends UseCase<T> {

    abstract public Observable<T> buildObservable(Params params);

    public void execute(DisposableObserver<T> observer, Params params, Scheduler scheduler) {
        final Observable<T> observable = addScheduler(this.buildObservable(params), scheduler);
        addDisposable(observable.subscribeWith(observer));
    }

}
