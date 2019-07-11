package com.jacky.innovex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ComputeActivity extends AppCompatActivity {

    ArrayList<Compute> data = new ArrayList<>();
    private static final String TAG = "TableActivity";

    private Adapter adapter;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.principal)
    TextView principal;
    @BindView(R.id.rate)
    TextView rate;
    @BindView(R.id.month)
    TextView month;
    @BindView(R.id.lower_limit)
    TextView lower_limit;
    @BindView(R.id.upper_limit)
    TextView upper_limit;

    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute);

        ButterKnife.bind(this);

        userData = Parcels.unwrap(getIntent().getParcelableExtra("userData"));
        principal.setText(userData.getPrincipal());
        rate.setText(userData.getRate());
        month.setText(userData.getMonths());
        lower_limit.setText(userData.getLowerLimit());
        upper_limit.setText(userData.getHighestLimit());




        createTable();
    }

    private void createTable() {
        Service service = new Service();

        service.getData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                data = service.processDataResults(response);
                ComputeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapter = new Adapter(data, ComputeActivity.this);
                        recyclerView.setAdapter(adapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ComputeActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
