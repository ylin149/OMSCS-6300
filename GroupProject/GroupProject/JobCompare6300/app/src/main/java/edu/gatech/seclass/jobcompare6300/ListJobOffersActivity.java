package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.DAL.JobDataModel;
import edu.gatech.seclass.jobcompare6300.Models.Job;

public class ListJobOffersActivity extends AppCompatActivity {
    private ListView jobOffersListView;
    ArrayList<Job> joblist = new ArrayList<>();
    JobDataModel dataModel;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_job_offers);
        jobOffersListView = (ListView) findViewById(R.id.listOffers);
        jobOffersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPosition = i;
            }
        });
        dataModel = new JobDataModel(this);

        refreshJobList();
    }

    private void refreshJobList(){
        List<String> listItems = new ArrayList<String>();
        //joblist populated here
        ArrayList<Job> tempJobList = dataModel.GetAll();
        joblist = new ArrayList<>();
        for (int i = 0; i<tempJobList.size(); i++) {
            if(tempJobList.get(i).IsCurrentJob == false){
                joblist.add(tempJobList.get(i));
                listItems.add(tempJobList.get(i).Title + " at " + tempJobList.get(i).Company);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_job_offers, R.id.jobOffers1TextView, listItems);
        selectedPosition = -1;
        jobOffersListView.setAdapter(adapter);
        //MainActivity.checkButtonText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.job_offer_menu, menu);
        return true;
    }

    public void addNewJob(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), JobOfferActivity.class);
        startActivityForResult(i, 1);
    }

    public void editExistingJob(MenuItem item) {
        if(selectedPosition >= 0) {
            Job jobToEdit = joblist.get(selectedPosition);
            Intent i = new Intent(getApplicationContext(), JobOfferActivity.class);
            //pass in id
            Bundle b = new Bundle();
            b.putInt("id", jobToEdit.id);
            i.putExtras(b);
            //open activity
            startActivityForResult(i, 1);
        }
    }

    public void deleteExistingJob(MenuItem item) {
        if(selectedPosition >= 0){
            Job jobToDelete = joblist.get(selectedPosition);
            boolean result = dataModel.Delete(jobToDelete.id);

            if(result){
                refreshJobList();
            }
            else{
                //TODO handle error
            }
        }
    }

    public void returnToMainMenu(MenuItem item) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refreshJobList();
    }
}