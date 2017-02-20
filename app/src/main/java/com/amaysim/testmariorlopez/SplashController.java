package com.amaysim.testmariorlopez;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

/**
 * Created by mario on 20/02/2017.
 */

public class SplashController extends Controller {

    private CountDownTimer countDownTimer;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_spash, container, false);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        countDownTimer = new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                //do something fancy :)
            }

            @Override
            public void onFinish() {
                Activity activity = getActivity();
                if (activity != null && !activity.isFinishing()) {
                    ((MainActivity) activity).showInfo();
                }
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        countDownTimer.cancel();
        countDownTimer = null;
    }
}
