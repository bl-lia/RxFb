package com.krsk.rxfb;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.krsk.rxfb.graphrequest.GraphJsonObjectResponse;
import com.krsk.rxfb.graphrequest.NewMeRequestOnSubscribe;

import rx.Observable;

/**
 * Created by yuki_fn on 2/2/16.
 */
public class RxGraphRequest {

    public static Observable<GraphJsonObjectResponse> newMeRequest(AccessToken accessToken, Bundle params) {
        return Observable.create(new NewMeRequestOnSubscribe(accessToken, params));
    }
}
