package io.github.luccastanan.bank.homeScreen;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.luccastanan.bank.Constants;
import io.github.luccastanan.bank.CustomApplication;
import io.github.luccastanan.bank.loginScreen.UserAccountModel;

interface StatementWorkerInput {
    void getStatements(int userId, HomeInteractorCallback callback);
}

public class StatementWorker implements StatementWorkerInput {
    @Override
    public void getStatements(int userId, final HomeInteractorCallback callback) {
        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.GET, Constants.URL_STATEMENTS + userId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson gson = new Gson();
                    StatementModel[] statements = gson.fromJson(response.getJSONArray("statementList").toString(), StatementModel[].class);
                    callback.onSuccess(new ArrayList<>(Arrays.asList(statements)));
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
        });
        // Adding String request to request queue
        CustomApplication.getInstance().addToRequestQueue(strReq, "login request");
    }
}
