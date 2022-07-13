package com.example.summaryui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.summaryui.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView setPortFolioValue, setLiabilityValue,setGoalNameValue,setAchievableGoalValue,setTotalGoalValue,setCurrentValue,setShortFallValue;
    int user_id = 155123;

    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        // getting our root layout in our view.
        View view = activityMainBinding.getRoot();

        // below line is to set
        // Content view for our layout.
        setContentView(view);
//        setContentView(R.layout.activity_main);
//        Button b = findViewById(R.id.btnEditData);
//        setPortFolioValue = findViewById(R.id.tvPortfolioValue);
//        setLiabilityValue = findViewById(R.id.tvLiabilitiesValue);
//        setAchievableGoalValue = findViewById(R.id.tvGoalAchieved1);
//        setTotalGoalValue = findViewById(R.id.tvGoalAchieved2);
//        setGoalNameValue = findViewById(R.id.tvRetirementGoal);
//        setCurrentValue = findViewById(R.id.tvRetirementGoalVal1);
//        setShortFallValue = findViewById(R.id.tvRetirementGoalVal2);
        activityMainBinding.btnEditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PostDataScreen.class);
                startActivity(i);
            }
        });

        activityMainBinding.btnLiability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AssetsFutureOptions.class);
                startActivity(i);
            }
        });
        getDataValues(user_id);

    }

    private void getDataValues(int user_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.fintoo.in/restapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroFitAPI retrofitAPI = retrofit.create(RetroFitAPI.class);

        GetModel modal = new GetModel();
        modal.setUser_id(user_id);

        // calling a method to create a post and passing our modal class.
        Call<GetModel> call = retrofitAPI.getPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<GetModel>() {
            @Override
            public void onResponse(Call<GetModel> call, Response<GetModel> response) {

                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                GetModel responseFromAPI = response.body();
//                setPortFolioValue.setText(responseFromAPI.data.portfolio_value);

//                setPortFolioValue.setText("₹ "+responseFromAPI.data.portfolio_value.toString());
                activityMainBinding.tvPortfolioValue.setText("₹ "+responseFromAPI.data.portfolio_value.toString());
                activityMainBinding.tvGoalAchieved1.setText(responseFromAPI.data.goalSummary.acheivable.toString());
                activityMainBinding.tvGoalAchieved2.setText(responseFromAPI.data.goalSummary.totalGoalCount.toString());
                activityMainBinding.tvRetirementGoal.setText(responseFromAPI.data.goalSummary.allGoals.get(1).goalName.toString());
                activityMainBinding.tvRetirementGoalVal1.setText("₹ "+responseFromAPI.data.goalSummary.allGoals.get(1).presentGoalValue.toString());
                activityMainBinding.tvRetirementGoalVal2.setText("₹ "+responseFromAPI.data.goalSummary.allGoals.get(1).shortFallValue.toString());
                activityMainBinding.tvLiabilitiesValue.setText("₹ "+responseFromAPI.data.liability_value.toString());
                System.out.println("Response From APi  ......" + responseFromAPI.data.portfolio_value);

            }

            @Override
            public void onFailure(Call<GetModel> call, Throwable t) {

                setPortFolioValue.setText("Error found is : " + t.getMessage());
                System.out.println("Error From APi  ......" + t.getMessage());
            }
        });

    }


}