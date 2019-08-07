package io.github.luccastanan.bank.loginScreen;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.github.luccastanan.bank.Constants;
import io.github.luccastanan.bank.CustomApplication;

interface UserAccountWorkerInput {
    //Define needed interfaces
    void doLogin(String user, String password, LoginInteractorCallback callback);
}

public class UserAccountWorker implements UserAccountWorkerInput {

    @Override
    public void doLogin(final String user, final String password, final LoginInteractorCallback callback) {

        StringRequest strReq = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Gson gson = new Gson();
                    UserAccountModel userAccount = gson.fromJson(jsonObject.getJSONObject("userAccount").toString(), UserAccountModel.class);
                    callback.onSuccess(userAccount);
                } catch (JSONException e) {
                    e.printStackTrace();
                    //TODO: tratar erro
                    callback.onError(null);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Coxinha", "Error: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user", user);
                params.put("password", password);
                return params;
            }
        };
        // Adding String request to request queue
        CustomApplication.getInstance().addToRequestQueue(strReq, "login request");
    }
}
