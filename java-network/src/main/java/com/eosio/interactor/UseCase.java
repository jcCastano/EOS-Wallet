package interactor;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jc on 5/31/18.
 */
abstract public class UseCase<T> {

    final CompositeDisposable disposables;

    protected UseCase() {
        this.disposables = new CompositeDisposable();
    }

    abstract public Observable<T> buildObservable();

    Observable<T> addScheduler(Observable<T> observable,Scheduler scheduler) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(scheduler);
    }

    public void execute(DisposableObserver<T> observer, Scheduler scheduler) {
        final Observable<T> observable = addScheduler(this.buildObservable(), scheduler);
        addDisposable(observable.subscribeWith(observer));
    }

    public void executeOnIO(DisposableObserver<T> observer) {
        execute(observer, Schedulers.io());
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

}
