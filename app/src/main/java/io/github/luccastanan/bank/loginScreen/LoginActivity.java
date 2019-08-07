package io.github.luccastanan.bank.loginScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.github.luccastanan.bank.CustomApplication;
import io.github.luccastanan.bank.R;
import io.github.luccastanan.bank.Util;
import io.github.luccastanan.bank.homeScreen.HomeActivity;


interface LoginActivityInput {
    public void displayLoginData(LoginViewModel viewModel);
}


public class LoginActivity extends AppCompatActivity
        implements LoginActivityInput {

    public static String TAG = LoginActivity.class.getSimpleName();
    LoginInteractorInput output;
    LoginRouter router;

    private LoginRequest loginRequest;

    private EditText edtUser;
    private TextView txvUser;
    private EditText edtPass;
    private TextView txvPass;
    private Button btnLogin;
    private ProgressBar loader;

    private boolean validatedInputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CustomApplication.getInstance(this);

        LoginConfigurator.INSTANCE.configure(this);

        instanceViews();
    }

    private void instanceViews() {
        edtUser = findViewById(R.id.edt_user);
        txvUser = findViewById(R.id.txv_user);
        edtPass = findViewById(R.id.edt_pass);
        txvPass = findViewById(R.id.txv_pass);
        btnLogin = findViewById(R.id.btn_login);
        loader = findViewById(R.id.loader);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validInputs()) {
                    btnLogin.setVisibility(View.GONE);
                    loader.setVisibility(View.VISIBLE);
                    loginRequest = new LoginRequest(edtUser.getText().toString(), edtPass.getText().toString());
                    output.fetchLoginData(loginRequest);
                }
            }
        });
    }

    private boolean validInputs() {
        boolean res = false;
        if (edtUser.getText().length() == 0) {
            res = showError(txvUser, R.string.error_enter_user, true);
        } else if (!Util.isEmail(edtUser.getText().toString()) && !Util.isCPF(edtUser.getText().toString())) {
            res = showError(txvUser, R.string.error_invalid_user, true);
        } else {
            showError(txvUser, 0, false);
        }

        if (edtPass.getText().length() == 0) {
            res = showError(txvPass, R.string.error_enter_pass, true);
        } else {
            showError(txvPass, 0, false);
        }

        return !res;
    }

    private boolean showError(TextView txv, int stringId, boolean visible) {
        if (visible) {
            txv.setText(stringId);
            txv.setVisibility(View.VISIBLE);
        } else {
            txv.setVisibility(View.GONE);
        }
        return visible;
    }


    @Override
    public void displayLoginData(LoginViewModel viewModel) {
        btnLogin.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
        Log.e(TAG, "displayLoginData() called with: viewModel = [" + viewModel.toString() + "]");
        //Toast.makeText(this, "Bem vindo " + viewModel.userAccountVM.name, Toast.LENGTH_SHORT).show();
        Intent newIntent = new Intent(this, HomeActivity.class);
        newIntent.putExtra("user", viewModel.userAccountVM);
        startActivity(newIntent);
        finish();
    }
}
