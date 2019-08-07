package io.github.luccastanan.bank.homeScreen;

import java.util.List;

public class HomeModel {
}

class HomeViewModel {
    List<StatementViewModel> listStatementVM;
}

class HomeRequest {
    int userId;
}

class HomeResponse {
    List<StatementModel> listStatement;

    @Override
    public String toString() {
        return listStatement.toString();
    }
}
