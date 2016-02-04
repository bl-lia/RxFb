package com.krsk.rxfb.graphrequest;

import com.facebook.GraphResponse;

import org.json.JSONObject;

/**
 * Created by yuki_fn on 2/4/16.
 */
public class GraphJsonObjectResponse {
    public final JSONObject jsonObject;
    public final GraphResponse graphResponse;

    public GraphJsonObjectResponse(JSONObject jsonObject, GraphResponse graphResponse) {
        this.jsonObject = jsonObject;
        this.graphResponse = graphResponse;
    }
}
