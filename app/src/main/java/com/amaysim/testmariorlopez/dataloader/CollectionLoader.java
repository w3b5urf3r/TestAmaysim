package com.amaysim.testmariorlopez.dataloader;

import android.content.Context;

import com.amaysim.testmariorlopez.entities.UserInfo;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by mario on 19/02/2017.
 */

public class CollectionLoader implements DataManager {

    private static String loadJSONFromAsset(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open("collection.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Timber.e(ex, "error while reading json assets");
            return ""; //empty string it's to simplify
        }
        return json;
    }

    private static UserInfo getData(String stringJson) {
        return new GsonBuilder().create().fromJson(stringJson, UserInfo.class);
    }

    @Override
    public Observable<UserInfo> loadData(Context context) {
        return Observable.just(CollectionLoader.loadJSONFromAsset(context))
                .observeOn(Schedulers.io())
                .flatMap(stringJson -> Observable.just(CollectionLoader.getData(stringJson)));
    }

}
