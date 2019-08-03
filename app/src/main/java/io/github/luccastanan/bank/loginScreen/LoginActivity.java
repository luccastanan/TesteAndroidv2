package io.github.luccastanan.bank.loginScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.github.luccastanan.bank.CustomApplication;
import io.github.luccastanan.bank.R;


interface LoginActivityInput {
    public void displayLoginData(LoginViewModel viewModel);
}


public class LoginActivity extends AppCompatActivity
        implements LoginActivityInput {

    public static String TAG = LoginActivity.class.getSimpleName();
    LoginInteractorInput output;
    LoginRouter router;

    LoginRequest loginRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        CustomApplication.getInstance(this);

        LoginConfigurator.INSTANCE.configure(this);

        instanceViews();
    }

    private void instanceViews() {
        final EditText edtUser = findViewById(R.id.edt_user);
        final EditText edtPass = findViewById(R.id.edt_pass);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest = new LoginRequest(edtUser.getText().toString(), edtPass.getText().toString());
                output.fetchLoginData(loginRequest);
            }
        });
    }


    @Override
    public void displayLoginData(LoginViewModel viewModel) {
        Log.e(TAG, "displayLoginData() called with: viewModel = [" + viewModel + "]");
        Toast.makeText(this, "Bem vindo " + viewModel.userAccount.name, Toast.LENGTH_SHORT).show();
    }
}
