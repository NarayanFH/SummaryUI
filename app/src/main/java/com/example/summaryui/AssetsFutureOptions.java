package com.example.summaryui;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.summaryui.databinding.ActivityAssetsFutureOptionsBinding;


public class AssetsFutureOptions extends AppCompatActivity implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    String[] categories = {"Equity", "Data structures",
            "Interview prep", "Algorithms",
            "DSA with java", "OS"};

    String[] subCategories = {"Future & Options", "Lita hexructures",
            "Uinterview prep", "Lalgorithms",
            "DSA with mava", "iOS"};
    String[] investmentFor = {"Self", "Family",
            "Member1", "Member2",
    };
    String[] assetToGoal = {"Select Option", "Education",
            "Retirement Goal", "No",
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

        categorySpinner = findViewById(R.id.assetCategorySpinner);
        subCategorySpinner = findViewById(R.id.assetSubCategorySpinner);
        investmentForSpinner = findViewById(R.id.spinnerInvestmentFor);
        linkAssetToGoalSpinner = findViewById(R.id.spinnerForLinkAssetToGoal);


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
        cat.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        mBinding.spinnerForLinkAssetToGoal.setAdapter(linkAssetToGoalAdapter);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(mBinding.switchAutomatedLinkage.isChecked()) {
            mBinding.tilSIPEndDate.setVisibility(View.VISIBLE);
            mBinding.tvLinkAssetToGoal.setVisibility(View.VISIBLE);
            mBinding.spinnerForLinkAssetToGoal.setVisibility(View.VISIBLE);
            mBinding.imvInfoSpinnerLinkAssetGoal.setVisibility(View.VISIBLE);

        }
        else {
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
