package io.github.luccastanan.bank.homeScreen;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.Credential;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.luccastanan.bank.R;
import io.github.luccastanan.bank.loginScreen.LoginActivity;
import io.github.luccastanan.bank.loginScreen.UserAccountViewModel;


interface HomeActivityInput {
    public void displayHomeData(HomeViewModel viewModel);
}

public class HomeActivity extends AppCompatActivity implements HomeActivityInput {

    public static String TAG = HomeActivity.class.getSimpleName();
    HomeInteractorInput output;
    HomeRouter router;

    private List<StatementViewModel> listStatementVM;
    private ListStatementAdapter listStatementAdapter;

    private UserAccountViewModel userAccountVM;

    private ConstraintLayout layout;
    private TextView txvAccount;
    private TextView txvBalance;

    private long lastBackPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        HomeConfigurator.INSTANCE.configure(this);
        HomeRequest aHomeRequest = new HomeRequest();
        aHomeRequest.userId = 1;

        userAccountVM = getIntent().getParcelableExtra("user");
        listStatementVM = new ArrayList<>();

        instanceViews();
        loadStaticData();

        output.fetchHomeData(aHomeRequest);
    }

    private void instanceViews() {
        layout = findViewById(R.id.layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txvAccount = findViewById(R.id.txv_account);
        txvBalance = findViewById(R.id.txv_balance);
        RecyclerView rcvStatements = findViewById(R.id.rcv_statements);
        listStatementAdapter = new ListStatementAdapter();
        rcvStatements.setAdapter(listStatementAdapter);
    }

    private void loadStaticData() {
        getSupportActionBar().setTitle(userAccountVM.name);
        txvAccount.setText(String.format(getString(R.string.label_account), userAccountVM.bankAccount, userAccountVM.agency));
        txvBalance.setText(String.format(getString(R.string.label_value), userAccountVM.balance));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        return true;
    }

    @Override
    public void displayHomeData(HomeViewModel viewModel) {
        listStatementVM.addAll(viewModel.listStatementVM);
        listStatementAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (lastBackPressTime < System.currentTimeMillis() - 3000 ){
            Snackbar.make(layout, R.string.back_to_exit, Snackbar.LENGTH_SHORT).show();
            lastBackPressTime = System.currentTimeMillis();
        }else {
            super.onBackPressed();
        }
    }

    private class ListStatementAdapter extends RecyclerView.Adapter<ListStatementAdapter.StatementViewHolder> {

        @NonNull
        @Override
        public StatementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_transition, viewGroup, false);
            return new StatementViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StatementViewHolder holder, int i) {
            StatementViewModel statementVM = listStatementVM.get(i);
            holder.txvTitle.setText(statementVM.title);
            holder.txvDate.setText(statementVM.date);
            holder.txvDesc.setText(statementVM.desc);
            holder.txvValue.setText(String.format(getString(R.string.label_value), statementVM.value));
        }

        @Override
        public int getItemCount() {
            return listStatementVM.size();
        }

        class StatementViewHolder extends RecyclerView.ViewHolder {
            TextView txvTitle;
            TextView txvDate;
            TextView txvDesc;
            TextView txvValue;

            StatementViewHolder(@NonNull View itemView) {
                super(itemView);
                txvTitle = itemView.findViewById(R.id.txv_title);
                txvDate = itemView.findViewById(R.id.txv_date);
                txvDesc = itemView.findViewById(R.id.txv_desc);
                txvValue = itemView.findViewById(R.id.txv_value);
            }
        }
    }
}
