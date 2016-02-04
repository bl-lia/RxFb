package com.krsk.rxfb.loginmanager;

import android.app.Activity;
import android.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Collection;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by yuki_fn on 2/2/16.
 */
public class LogInWithReadPermissionsOnSubscribe implements Observable.OnSubscribe<LoginResult> {

    private final LoginManager mLoginManager;
    private final CallbackManager mCallbackManager;
    private final Collection mPermissions;

    private final Activity mActivity;
    private final Fragment mFragment;
    private final android.support.v4.app.Fragment mFragmentV4;

    public LogInWithReadPermissionsOnSubscribe(LoginManager loginManager, CallbackManager callbackManager, Activity activity, Collection permissions) {
        mLoginManager = loginManager;
        mCallbackManager = callbackManager;
        mActivity = activity;
        mFragment = null;
        mFragmentV4 = null;
        mPermissions = permissions;
    }

    public LogInWithReadPermissionsOnSubscribe(LoginManager loginManager, CallbackManager callbackManager, Fragment fragment, Collection permissions) {
        mLoginManager = loginManager;
        mCallbackManager = callbackManager;
        mActivity = null;
        mFragment = fragment;
        mFragmentV4 = null;
        mPermissions = permissions;
    }

    public LogInWithReadPermissionsOnSubscribe(LoginManager loginManager, CallbackManager callbackManager, android.support.v4.app.Fragment fragment, Collection permissions) {
        mLoginManager = loginManager;
        mCallbackManager = callbackManager;
        mActivity = null;
        mFragment = null;
        mFragmentV4 = fragment;
        mPermissions = permissions;
    }

    @Override
    public void call(final Subscriber<? super LoginResult> subscriber) {

        final FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                subscriber.onNext(loginResult);
                subscriber.onCompleted();
            }

            @Override
            public void onCancel() {
                subscriber.onCompleted();
            }

            @Override
            public void onError(FacebookException error) {
                subscriber.onError(error);
            }
        };

        mLoginManager.registerCallback(mCallbackManager, callback);

        if (mActivity != null) {
            mLoginManager.logInWithReadPermissions(mActivity, mPermissions);
            return;
        }

        if (mFragment != null) {
            mLoginManager.logInWithReadPermissions(mFragment, mPermissions);
            return;
        }

        if (mFragmentV4 != null) {
            mLoginManager.logInWithReadPermissions(mFragmentV4, mPermissions);
            return;
        }

        subscriber.onError(new UnsupportedOperationException("No Activity or Fragment assigned!"));
    }
}
