package io.github.luccastanan.bank.loginScreen;

import io.github.luccastanan.bank.ErrorModel;

interface LoginInteractorInput {
    public void fetchLoginData(LoginRequest request);
}

interface LoginInteractorCallback {
    void onSuccess(UserAccountModel userAccount);
    void onError(ErrorModel error);
}

public class LoginInteractor implements LoginInteractorInput, LoginInteractorCallback {

    public static String TAG = LoginInteractor.class.getSimpleName();
    public LoginPresenterInput output;
    public UserAccountWorkerInput aUserAccountWorkerInput;

    public UserAccountWorkerInput getLoginWorkerInput() {
        if (aUserAccountWorkerInput == null) return new UserAccountWorker();
        return aUserAccountWorkerInput;
    }

    public void setLoginWorkerInput(UserAccountWorkerInput aUserAccountWorkerInput) {
        this.aUserAccountWorkerInput = aUserAccountWorkerInput;
    }

    @Override
    public void fetchLoginData(LoginRequest request) {
        aUserAccountWorkerInput = getLoginWorkerInput();

        // Call the workers
        //loginResponse.userAccount =aUserAccountWorkerInput.doLogin();
        aUserAccountWorkerInput.doLogin(request.user, request.password, this);

    }

    @Override
    public void onSuccess(UserAccountModel userAccount) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.userAccount = userAccount;
        output.presentLoginData(loginResponse);

    }

    @Override
    public void onError(ErrorModel error) {

    }
}
