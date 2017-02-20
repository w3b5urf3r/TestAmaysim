package com.amaysim.testmariorlopez.dataloader;

import android.content.Context;

import com.amaysim.testmariorlopez.entities.UserInfo;

import io.reactivex.Observable;

/**
 * Created by mario on 20/02/2017.
 */

interface DataManager {
    Observable<UserInfo> loadData(Context context);
}
