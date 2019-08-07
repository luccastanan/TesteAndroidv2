package io.github.luccastanan.bank.loginScreen;

public class LoginModel {
}

class LoginViewModel {
    UserAccountViewModel userAccountVM;

    @Override
    public String toString() {
        return userAccountVM.toString();
    }
}

class LoginRequest {
    String user;
    String password;

    LoginRequest(String _user, String _password){
        this.user = _user;
        this.password = _password;
    }
}

class LoginResponse {
    UserAccountModel userAccount;
}
