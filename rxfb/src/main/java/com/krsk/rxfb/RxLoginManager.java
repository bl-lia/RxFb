package com.krsk.rxfb;

import android.app.Activity;
import android.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.krsk.rxfb.loginmanager.LogInWithReadPermissionsOnSubscribe;

import java.util.Collection;

import rx.Observable;

/**
 * Created by yuki_fn on 2/2/16.
 */
public class RxLoginManager {

    private final LoginManager mLoginManager;

    public static RxLoginManager from(LoginManager manager) {
        return new RxLoginManager(manager);
    }

    public RxLoginManager(LoginManager manager) {
        mLoginManager = manager;
    }

    public Observable<LoginResult> logInWithReadPermissions(CallbackManager callbackManager, Activity activity, Collection permissions) {
        return Observable.create(new LogInWithReadPermissionsOnSubscribe(mLoginManager, callbackManager, activity, permissions));
    }

    public Observable<LoginResult> logInWithReadPermissions(CallbackManager callbackManager, Fragment fragment, Collection permissions) {
        return Observable.create(new LogInWithReadPermissionsOnSubscribe(mLoginManager, callbackManager, fragment, permissions));
    }

    public Observable<LoginResult> logInWithReadPermissions(CallbackManager callbackManager, android.support.v4.app.Fragment fragment, Collection permissions) {
        return Observable.create(new LogInWithReadPermissionsOnSubscribe(mLoginManager, callbackManager, fragment, permissions));
    }
}
