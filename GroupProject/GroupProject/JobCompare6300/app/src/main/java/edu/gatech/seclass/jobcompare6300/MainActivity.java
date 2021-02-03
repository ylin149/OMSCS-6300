package edu.gatech.seclass.jobcompare6300;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import edu.gatech.seclass.jobcompare6300.DAL.JobDataModel;

public class MainActivity extends AppCompatActivity {
    private Button btnCurrentJobDetails;
    private Button btnListJobOffer;
    private Button btnComparisonSettings;
    private Button btnCompareJobOffers;
    public  JobDataModel dataModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCurrentJobDetails = (Button) findViewById(R.id.btnCurrentJob);
        btnListJobOffer = (Button) findViewById(R.id.btnListOffers);
        btnComparisonSettings = (Button) findViewById(R.id.btnComparisonSettings);
        btnCompareJobOffers = (Button) findViewById(R.id.btnCompareOffers);
        dataModel = new JobDataModel(this);
        if(dataModel.GetAll().size() <= 1) {
            btnCompareJobOffers.setText("Must add jobs to compare");
            //btnCompareJobOffers.setBackgroundColor(Color.DKGRAY);
        }  else {
            btnCompareJobOffers.setText("Compare Job Offers");
            //btnCompareJobOffers.setBackgroundColor(Color.LTGRAY);
        }
        setBtnCurrentJobDetails();
        setBtnListJobOfferClick();
        setBtnComparisonSettingsClick();
        setBtnCompareJobOffersClick();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dataModel.GetAll().size() <= 1) {
            btnCompareJobOffers.setText("Must add jobs to compare");
        } else {
            btnCompareJobOffers.setText("Compare Job Offers");
        }
    }

    void setBtnCurrentJobDetails() {
        btnCurrentJobDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CurrentJobOfferActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    private void setBtnListJobOfferClick() {
        btnListJobOffer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListJobOffersActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    private void setBtnComparisonSettingsClick() {
        btnComparisonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ComparisonSettingsActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    private void setBtnCompareJobOffersClick() {
        btnCompareJobOffers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), JobComparisonSelectionActivity.class);
                if(dataModel.GetAll().size() <= 1) {
                    btnCompareJobOffers.setText("Must add jobs to compare");
                    //btnCompareJobOffers.setBackgroundColor(Color.DKGRAY);
                } else {
                    btnCompareJobOffers.setText("Compare Job Offers");
                    //btnCompareJobOffers.setBackgroundColor(Color.LTGRAY);
                    startActivityForResult(i, 1);
                }
            }
        });
    }
}