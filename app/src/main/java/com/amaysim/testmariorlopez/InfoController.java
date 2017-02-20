package com.amaysim.testmariorlopez;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amaysim.testmariorlopez.databinding.ControllerInfoBinding;
import com.amaysim.testmariorlopez.dataloader.CollectionLoader;
import com.amaysim.testmariorlopez.entities.UserInfo;
import com.bluelinelabs.conductor.Controller;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by mario on 19/02/2017.
 */
interface InfoView {
    void reloadData(UserInfo userInfo);
}

public class InfoController extends Controller implements InfoView {
    private final InfoPresenter infoPresenter = new InfoPresenter();

    private ControllerInfoBinding controllerInfoBinding;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_info, container, false);
        controllerInfoBinding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        infoPresenter.bindView(this);
        infoPresenter.showInfo(getApplicationContext());

    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        infoPresenter.unbindView(this);
        controllerInfoBinding = null;
    }


    @Override
    public void reloadData(UserInfo userInfo) {
        if (controllerInfoBinding != null) {
            controllerInfoBinding.setUserInfo(userInfo);
            controllerInfoBinding.notifyPropertyChanged(BR.userInfo);
        }
    }

    class InfoPresenter extends BasePresenter<InfoView> {
        public void showInfo(Context context) {
            {
                //for simplicity I skip the user feedback on loading

            }
            final Disposable disposable = new CollectionLoader().loadData(context)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(userInfo -> {
                                final InfoView view = view();

                                if (view != null) {
                                    view.reloadData(userInfo);
                                }
                            },
                            error -> {
                                //skipped for simplicity
                            }
                    );

            disposeOnUnbindView(disposable);

        }
    }
}