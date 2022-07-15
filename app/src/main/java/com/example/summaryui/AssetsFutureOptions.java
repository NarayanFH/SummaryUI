package com.example.summaryui;

//{
//        " user_id": 186159,
//        " fp_log_id": 6365
//        }

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.summaryui.databinding.ActivityAssetsFutureOptionsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AssetsFutureOptions extends AppCompatActivity implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    int user_id = 155123;
    int parent_user_id = 186159;
    int fp_log_id = 6365;
    String[] categories = {"Equity", "Debt",
            "Real Estate", "Liquid",
            "Alternate", "OS"};

    String[] subCategories = {"Future & Options", "Equity Shares",
            "ESOPs", "Test2",
    };
    String[] investmentFor = {"Self",
    };
    String[] assetToGoal = {"Select Option",
    };

    Spinner categorySpinner, subCategorySpinner, investmentForSpinner, linkAssetToGoalSpinner;
    Switch switchOneTimeOrRecurring, switchAutomatedLinkage;
    TextView tvDateOfPurchase;

    ActivityAssetsFutureOptionsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_assets_future_options);
        mBinding = ActivityAssetsFutureOptionsBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        // below line is to set
        // Content view for our layout.
        setContentView(view);

        mBinding.switchEquityRecurring.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        mBinding.switchAutomatedLinkage.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        mBinding.imvInfoInvestedValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Info Value will be update based on values", Toast.LENGTH_SHORT).show();
            }
        });

        categorySpinner = findViewById(R.id.assetCategorySpinner);
        subCategorySpinner = findViewById(R.id.assetSubCategorySpinner);
        investmentForSpinner = findViewById(R.id.spinnerInvestmentFor);
        linkAssetToGoalSpinner = findViewById(R.id.spinnerForLinkAssetToGoal);

        getDataValues(parent_user_id, fp_log_id,user_id);
        getGoalsValues(user_id,fp_log_id);
        mBinding.assetCategorySpinner.setOnItemSelectedListener(this);
        ArrayAdapter cat
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                categories);

        // set simple layout resource file
        // for each item of spinner
        cat.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        mBinding.assetCategorySpinner.setAdapter(cat);


        mBinding.assetSubCategorySpinner.setOnItemSelectedListener(this);
        ArrayAdapter sub
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                subCategories);

        // set simple layout resource file
        // for each item of spinner
        sub.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        mBinding.assetSubCategorySpinner.setAdapter(sub);


        mBinding.spinnerInvestmentFor.setOnItemSelectedListener(this);
        ArrayAdapter investmentSpin
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, investmentFor
        );
        // set simple layout resource file
        // for each item of spinner
        investmentSpin.setDropDownViewResource(
                android.R.layout.simple_spinner_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        mBinding.spinnerInvestmentFor.setAdapter(investmentSpin);


        mBinding.spinnerForLinkAssetToGoal.setOnItemSelectedListener(this);
        ArrayAdapter linkAssetToGoalAdapter
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                assetToGoal);
        // set simple layout resource file
        // for each item of spinner
        linkAssetToGoalAdapter.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        mBinding.spinnerForLinkAssetToGoal.setAdapter(linkAssetToGoalAdapter);


    }

    private void getDataValues(int parent_user_id, int fp_log_id, int user_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.fintoo.in/restapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroFitAPI retrofitAPI = retrofit.create(RetroFitAPI.class);

        GetFamilyModal modal = new GetFamilyModal();
        modal.setParent_user_id(parent_user_id);
        modal.setFp_log_id(fp_log_id);

        // calling a method to create a post and passing our modal class.
        Call<GetFamilyModal> call = retrofitAPI.getFamilyDetails(parent_user_id, fp_log_id);

        // on below line we are executing our method.
        call.enqueue(new Callback<GetFamilyModal>() {
            @Override
            public void onResponse(Call<GetFamilyModal> call, Response<GetFamilyModal> response) {

                Toast.makeText(AssetsFutureOptions.this, "Data added to API", Toast.LENGTH_SHORT).show();

                GetFamilyModal responseFromAPI = response.body();

                List arrayElements = responseFromAPI.dataList;
                investmentFor = new String[arrayElements.size()];


                for (int i = 0; i < arrayElements.size(); i++) {
                    investmentFor[i] = responseFromAPI.dataList.get(i).relationname + " " + responseFromAPI.dataList.get(i).first_name;
                }
                updateItems();
//                System.out.println("Response From APi  ......" + responseFromAPI.dataList.get(0).first_name);
            }


            @Override
            public void onFailure(Call<GetFamilyModal> call, Throwable t) {

                System.out.println("Error From APi  ......" + t.getMessage());
            }
        });

    }


    // Goals Values

    private void getGoalsValues(int user_id,int fp_log_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.fintoo.in/restapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroFitAPI retrofitAPI = retrofit.create(RetroFitAPI.class);

        GetGoalsModal modals = new GetGoalsModal();
        modals.set_user_id(user_id);
        modals.setFp_log_id(fp_log_id);

        // calling a method to create a post and passing our modal class.
        Call<GetGoalsModal> call = retrofitAPI.getGoalsDetails(modals);

        // on below line we are executing our method.
        call.enqueue(new Callback<GetGoalsModal>() {
            @Override
            public void onResponse(Call<GetGoalsModal> call, Response<GetGoalsModal> responses) {

                Toast.makeText(AssetsFutureOptions.this, "Data added to API", Toast.LENGTH_SHORT).show();

                GetGoalsModal responseFromAPIs = responses.body();

                System.out.println("Gooooools2 ......" + responseFromAPIs.toString());

                List arrayElement = responseFromAPIs.dataGoal;
                assetToGoal = new String[arrayElement.size()];

                for (int i = 0; i < arrayElement.size(); i++) {
                    assetToGoal[i] =  responseFromAPIs.dataGoal.get(i).goal_name;

                }
                updateGoalItems();
                System.out.println("Gooooools ......" + responseFromAPIs.dataGoal.get(2).goal_name);
            }


            @Override
            public void onFailure(Call<GetGoalsModal> call, Throwable t) {

                System.out.println("Error Goools Noooo Goals APi  ......" + t.getMessage());
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (mBinding.switchAutomatedLinkage.isChecked()) {
            mBinding.tilSIPEndDate.setVisibility(View.VISIBLE);
            mBinding.tvLinkAssetToGoal.setVisibility(View.VISIBLE);
            mBinding.spinnerForLinkAssetToGoal.setVisibility(View.VISIBLE);
            mBinding.imvInfoSpinnerLinkAssetGoal.setVisibility(View.VISIBLE);

        } else {
            mBinding.tilSIPEndDate.setVisibility(View.GONE);
            mBinding.tvLinkAssetToGoal.setVisibility(View.GONE);
            mBinding.spinnerForLinkAssetToGoal.setVisibility(View.GONE);
            mBinding.imvInfoSpinnerLinkAssetGoal.setVisibility(View.GONE);
        }

        if (mBinding.switchEquityRecurring.isChecked()) {
            mBinding.tilSIPStartDateRecurring.setVisibility(View.VISIBLE);
            mBinding.tilSIPAmountRecurring.setVisibility(View.VISIBLE);
            mBinding.tilTotalInvestedRecurring.setVisibility(View.VISIBLE);
            mBinding.tilNoOfSharesRecurring.setVisibility(View.VISIBLE);
            mBinding.tilCurrentPriceRecurring.setVisibility(View.VISIBLE);
            mBinding.tilCurrentValueRecurring.setVisibility(View.VISIBLE);

            mBinding.tilDateOfPurchase.setVisibility(View.INVISIBLE);
            mBinding.tilNoOfShares.setVisibility(View.INVISIBLE);
            mBinding.tilAvgBuyPrice.setVisibility(View.INVISIBLE);
            mBinding.tilInvestedValue.setVisibility(View.INVISIBLE);
            mBinding.tilCurrentPrice.setVisibility(View.INVISIBLE);
            mBinding.tilCurrentValue.setVisibility(View.INVISIBLE);
        } else {
            mBinding.tilSIPStartDateRecurring.setVisibility(View.INVISIBLE);
            mBinding.tilSIPAmountRecurring.setVisibility(View.INVISIBLE);
            mBinding.tilTotalInvestedRecurring.setVisibility(View.INVISIBLE);
            mBinding.tilNoOfSharesRecurring.setVisibility(View.INVISIBLE);
            mBinding.tilCurrentPriceRecurring.setVisibility(View.INVISIBLE);
            mBinding.tilCurrentValueRecurring.setVisibility(View.INVISIBLE);

            mBinding.tilDateOfPurchase.setVisibility(View.VISIBLE);
            mBinding.tilNoOfShares.setVisibility(View.VISIBLE);
            mBinding.tilAvgBuyPrice.setVisibility(View.VISIBLE);
            mBinding.tilInvestedValue.setVisibility(View.VISIBLE);
            mBinding.tilCurrentPrice.setVisibility(View.VISIBLE);
            mBinding.tilCurrentValue.setVisibility(View.VISIBLE);
        }
    }


    public void updateItems() {
        mBinding.spinnerInvestmentFor.setOnItemSelectedListener(this);
        ArrayAdapter investmentSpin
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, investmentFor
        );
        // set simple layout resource file
        // for each item of spinner
        investmentSpin.setDropDownViewResource(
                android.R.layout.simple_spinner_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        mBinding.spinnerInvestmentFor.setAdapter(investmentSpin);
    }

    public void updateGoalItems() {
        mBinding.spinnerForLinkAssetToGoal.setOnItemSelectedListener(this);
        ArrayAdapter linkAssetToGoalAdapter
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                assetToGoal);
        // set simple layout resource file
        // for each item of spinner
        linkAssetToGoalAdapter.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        mBinding.spinnerForLinkAssetToGoal.setAdapter(linkAssetToGoalAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
