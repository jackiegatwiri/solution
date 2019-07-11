package com.jacky.innovex;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

@BindView(R.id.button)
    Button button;

    UserData userData;
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Random random = new Random();

        int principalAmount =random.nextInt(1000000 - 1000 + 1) + 1000;
        int interest = random.nextInt(40 - 10 + 1) + 10;
        int month = random.nextInt(18 - 3 + 1) + 3;
        int lowerLimitRange = random.nextInt(10000 - 100 + 1) + 100;
        int upperLimitRange = random.nextInt(100000 - 50000 + 1) + 50000;


        userData = new UserData(String.valueOf(principalAmount),String.valueOf(interest),String.valueOf(month), String.valueOf(lowerLimitRange),
                String.valueOf(upperLimitRange));
        Gson gson = new Gson();
        String output = gson.toJson(userData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(output);
            }
        });


    }

    public void getData(String output)
    {

        Service service = new Service();
        service.postData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        Intent intent = new Intent(MainActivity.this, ComputeActivity.class);
                        intent.putExtra("userData", Parcels.wrap(userData));

                        startActivity(intent);
                    }
                });
            }
        },output);
    }

}
