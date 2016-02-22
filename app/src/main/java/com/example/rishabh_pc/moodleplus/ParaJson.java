package com.example.rishabh_pc.moodleplus;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.rishabh_pc.moodleplus.Login;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rishabh-pc on 15-Jan-16.
 */
public class ParaJson extends JsonObjectRequest {
    public Map<String, String> params = new HashMap<String, String>();

    public ParaJson (String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        super(Method.GET, url, null, listener, errorListener);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        // since we don't know which of the two underlying network vehicles
        // will Volley use, we have to handle and store session cookies manually
        Login.get().checkSessionCookie(response.headers);

        return super.parseNetworkResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();

        if (headers == null
                || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }

        Login.get().addSessionCookie(headers);

        return headers;
    }
}
