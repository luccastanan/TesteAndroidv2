package io.github.luccastanan.bank.homeScreen;


import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface HomePresenterInput {
    public void presentHomeData(HomeResponse response);
}


public class HomePresenter implements HomePresenterInput {

    public static String TAG = HomePresenter.class.getSimpleName();

    public WeakReference<HomeActivityInput> output;


    @Override
    public void presentHomeData(HomeResponse response) {
        Log.e(TAG, "presentHomeData() called with: response = " + response.toString());
        HomeViewModel homeVM = new HomeViewModel();
        List<StatementViewModel> listStatementVM = new ArrayList<>();

        for (StatementModel statement : response.listStatement) {
            StatementViewModel statementVM = new StatementViewModel();
            statementVM.title = statement.title;
            statementVM.desc = statement.desc;
            statementVM.date = statement.date;
            statementVM.value = statement.value;
            listStatementVM.add(statementVM);
        }

        homeVM.listStatementVM = listStatementVM;
        output.get().displayHomeData(homeVM);
    }
}
