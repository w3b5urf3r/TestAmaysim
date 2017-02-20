package com.amaysim.testmariorlopez;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

public class MainActivity extends AppCompatActivity {


    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup container = (ViewGroup) findViewById(R.id.controller_container);
        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new LoginController()));
        }
    }

    //back stack is not handled in this example.
    public void showInfo() {
        router.pushController(RouterTransaction.with(new InfoController())
                .pushChangeHandler(FadeChangeHandler.fromBundle(null))
                .popChangeHandler(FadeChangeHandler.fromBundle(null)));
    }

    public void showSplashScreen() {
        router.pushController(RouterTransaction.with(new SplashController())
                .pushChangeHandler(FadeChangeHandler.fromBundle(null))
                .popChangeHandler(FadeChangeHandler.fromBundle(null)));
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }
}
