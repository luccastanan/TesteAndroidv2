package io.github.luccastanan.bank.loginScreen;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    public void presentLoginData(LoginResponse response);
}


public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();
    public WeakReference<LoginActivityInput> output;


    @Override
    public void presentLoginData(LoginResponse response) {
        LoginViewModel loginVM = new LoginViewModel();
        UserAccountViewModel userAccountVM = new UserAccountViewModel();
        userAccountVM.userId = response.userAccount.userId;
        userAccountVM.name = response.userAccount.name;
        userAccountVM.bankAccount = response.userAccount.bankAccount;
        userAccountVM.agency = response.userAccount.agency;
        userAccountVM.balance = response.userAccount.balance;
        loginVM.userAccount = userAccountVM;
        output.get().displayLoginData(loginVM);
    }


}
