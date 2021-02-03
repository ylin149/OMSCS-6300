package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.DAL.JobDataModel;
import edu.gatech.seclass.jobcompare6300.Models.Job;

public class JobComparisonSelectionActivity extends AppCompatActivity {
    private ListView jobOffersListView1;
    private ListView jobOffersListView2;
    ArrayList<Job> joblist = new ArrayList<>();
    JobDataModel dataModel;
    int selectedPosition1 = -1;
    int selectedPosition2 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_comparison_selection);
        jobOffersListView1 = (ListView) findViewById(R.id.listJobs1);
        jobOffersListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPosition1 = i;
            }
        });
        jobOffersListView2 = (ListView) findViewById(R.id.listJobs2);
        jobOffersListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPosition2 = i;
            }
        });
        dataModel = new JobDataModel(this);

        refreshJobList();
    }

    private void refreshJobList(){
        List<String> listItems = new ArrayList<String>();
        //joblist populated here
        joblist = dataModel.GetAll();
        for (int i = 0; i<joblist.size(); i++) {
            if(joblist.get(i).IsCurrentJob == false){
                listItems.add(joblist.get(i).Title + " at " + joblist.get(i).Company);
            }
            else{
                listItems.add("CURRENT-" + joblist.get(i).Title + " at " + joblist.get(i).Company);
            }
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.list_job_offers, R.id.jobOffers1TextView, listItems);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.list_job_offers, R.id.jobOffers1TextView, listItems);
        selectedPosition1 = -1;
        selectedPosition2 = -1;
        jobOffersListView1.setAdapter(adapter1);
        jobOffersListView2.setAdapter(adapter2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.comparison_selection_menu, menu);
        return true;
    }

    public void compareJobs(MenuItem item) {

        if(selectedPosition1 >= 0 && selectedPosition2 >= 0 ){
            Job job1 = joblist.get(selectedPosition1);
            Job job2 = joblist.get(selectedPosition2);

            Intent i = new Intent(getApplicationContext(), CompareJobOffersActivity.class);
            //pass in id
            Bundle b = new Bundle();
            b.putInt("job1id", job1.id);
            b.putInt("job2id", job2.id);
            i.putExtras(b);
            //open activity
            startActivityForResult(i, 1);
        }
    }

    public void returnToMainMenu(MenuItem item) {
        finish();
    }
}
