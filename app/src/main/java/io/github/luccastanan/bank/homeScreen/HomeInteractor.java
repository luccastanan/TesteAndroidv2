package io.github.luccastanan.bank.homeScreen;

import java.util.List;

import io.github.luccastanan.bank.ErrorModel;
import io.github.luccastanan.bank.loginScreen.UserAccountModel;

interface HomeInteractorInput {
    public void fetchHomeData(HomeRequest request);
}

interface HomeInteractorCallback {
    void onSuccess(List<StatementModel> listStatement);
    void onError(ErrorModel error);
}

public class HomeInteractor implements HomeInteractorInput, HomeInteractorCallback  {

    public static String TAG = HomeInteractor.class.getSimpleName();
    public HomePresenterInput output;
    public StatementWorkerInput aStatementWorkerInput;

    public StatementWorkerInput getHomeWorkerInput() {
        if (aStatementWorkerInput == null) return new StatementWorker();
        return aStatementWorkerInput;
    }

    public void setHomeWorkerInput(StatementWorkerInput aStatementWorkerInput) {
        this.aStatementWorkerInput = aStatementWorkerInput;
    }

    @Override
    public void fetchHomeData(HomeRequest request) {
        aStatementWorkerInput = getHomeWorkerInput();
        aStatementWorkerInput.getStatements(request.userId, this);
    }

    @Override
    public void onSuccess(List<StatementModel> listStatement) {
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.listStatement = listStatement;
        output.presentHomeData(homeResponse);
    }

    @Override
    public void onError(ErrorModel error) {

    }
}
