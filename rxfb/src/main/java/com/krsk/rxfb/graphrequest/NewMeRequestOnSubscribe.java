package com.krsk.rxfb.graphrequest;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONObject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by yuki_fn on 2/2/16.
 */
public class NewMeRequestOnSubscribe implements Observable.OnSubscribe<GraphJsonObjectResponse> {

    private final AccessToken mAccessToken;
    private final Bundle mParams;

    public NewMeRequestOnSubscribe(AccessToken accessToken, Bundle params) {
        mAccessToken = accessToken;
        mParams = params;
    }

    @Override
    public void call(final Subscriber<? super GraphJsonObjectResponse> subscriber) {

        final GraphRequest.GraphJSONObjectCallback callback = new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse graphResponse) {
                final GraphJsonObjectResponse response = new GraphJsonObjectResponse(object, graphResponse);

                subscriber.onNext(response);
                subscriber.onCompleted();
            }
        };

        final GraphRequest graphRequest = GraphRequest.newMeRequest(mAccessToken, callback);
        graphRequest.setParameters(mParams);
        graphRequest.executeAsync();
    }
}
