package com.amaysim.testmariorlopez;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by mario on 18/02/2017.
 */

class BasePresenter<V> {

    @NonNull
    private final CompositeDisposable disposableToDispose = new CompositeDisposable();

    @Nullable
    private volatile V view;

    @CallSuper
    public void bindView(@NonNull V view) {
        final V previousView = this.view;

        if (previousView != null) {
            throw new IllegalStateException("Previous view is not unbounded! previousView = " + previousView);
        }

        this.view = view;
    }

    @Nullable
    V view() {
        return view;
    }

    final void disposeOnUnbindView(@NonNull Disposable disposable, @NonNull Disposable... disposables) {
        disposableToDispose.add(disposable);

        for (Disposable d : disposables) {
            disposableToDispose.add(d);
        }
    }

    @CallSuper
    @SuppressWarnings("PMD.CompareObjectsWithEquals")
    public void unbindView(@NonNull V view) {
        final V previousView = this.view;

        if (previousView == view) {
            this.view = null;
        } else {
            throw new IllegalStateException("Unexpected view! previousView = " + previousView + ", view to unbind = " + view);
        }

        // Unsubscribe all subscriptions that need to be unsubscribed in this lifecycle state.
        disposableToDispose.clear();
    }
}
