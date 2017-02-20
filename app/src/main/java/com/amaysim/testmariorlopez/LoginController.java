package com.amaysim.testmariorlopez;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.amaysim.testmariorlopez.dataloader.CollectionLoader;
import com.amaysim.testmariorlopez.entities.PlanItem;
import com.bluelinelabs.conductor.Controller;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

interface LoginView {
    void showLoadingUi(boolean visible);

    void showErrorUi(@NonNull Throwable error);

    void login(boolean logged);
}

/**
 * Created by mario on 18/02/2017.
 */
public class LoginController extends Controller implements LoginView {

    //This instantiation needs to be done with a sub module using dagger2 dependency injection
    private LoginPresenter presenter = new LoginPresenter();
    private View progress;
    private EditText msn;
    private Button loginBtn;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_login, container, false);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        presenter.bindView(this);
        bindViews(view);
        view.findViewById(R.id.login_button).setOnClickListener(viewOnClick ->
                presenter.login(getApplicationContext(),
                        msn.getText().toString()));
    }

    //we can use Butternkife for injecting the view and have a cleaner code
    //butterknife doesn't work int his configuration, yet
    private void bindViews(View view) {
        progress = view.findViewById(R.id.progress_bar);
        msn = (EditText) view.findViewById(R.id.msn);
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        presenter.unbindView(this);
    }


    @Override
    public void showLoadingUi(boolean visible) {
        progress.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorUi(@NonNull Throwable error) {
        //todo do something
    }

    @Override
    public void login(boolean logged) {
        if (logged) {
            Activity activity = getActivity();
            if (activity != null) {
                ((MainActivity) activity).showSplashScreen();
            }
        } else {
            //give feedback to the user
        }
    }
}

class LoginPresenter extends BasePresenter<LoginView> {
    public void login(Context context, String msn) {
        {
            final LoginView view = view();

            if (view != null) {
                view.showLoadingUi(true);
            }
        }
        //collection loader can be easily injected
        //the use of the interface DataManager allow a better testability
        final Disposable disposable = new CollectionLoader().loadData(context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(userInfo -> {
                            final LoginView view = view();

                            PlanItem planItemWithMsn = null;
                            for (PlanItem planItem : userInfo.getPlanItems()) {
                                if (planItem.getType().equals(PlanItem.SERVICES_KEY)) {
                                    planItemWithMsn = planItem;
                                    break;
                                }
                            }
                            boolean logged = false;
                            //not having the specs for the model i consider attributes and all
                            //the remaining field always !=null
                            if (TextUtils.equals(planItemWithMsn.getAttributes().getMsn(), msn)) {
                                logged = true;
                            }
                            logged = true;
                            if (view != null) {
                                view.showLoadingUi(false);
                                view.login(logged);
                            }
                        },
                        error -> {
                            final LoginView view = view();
                            if (view != null) {
                                view.showErrorUi(error);
                            }
                        }
                );
        // Prevent memory leak.
        disposeOnUnbindView(disposable);

    }
}