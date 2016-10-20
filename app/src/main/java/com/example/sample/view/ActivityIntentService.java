package com.example.sample.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.sample.R;
import com.example.sample.adapter.RecyclerAdapter2;
import com.example.sample.data.Alert;
import com.example.sample.data.AlertWrapper;
import com.example.sample.recievers.IntentServiceReciever;
import com.example.sample.services.MyIntentService;

import java.util.ArrayList;
import java.util.List;

public class ActivityIntentService extends AppCompatActivity implements IntentServiceReciever.Receiver {

    private String serviceUrl = "http://192.168.0.62/leadme/serviceleadme/getAlertMessages";
    private RecyclerView recyclerView;
    private RecyclerAdapter2 recyclerAdapter;
    private List<Alert> userArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private IntentServiceReciever mReceiver;
   // final String url = "http://javatechig.com/api/get_category_posts/?dev=1&slug=android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Allow activity to show indeterminate progressbar */
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        /* Set activity layout */
        setContentView(R.layout.activity_activity_intent_service);

        /* Initialize listView */
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(ActivityIntentService.this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerAdapter = new RecyclerAdapter2(ActivityIntentService.this, (ArrayList)userArrayList);
        recyclerView.setAdapter(recyclerAdapter);

        /* Starting Download Service */
        mReceiver = new IntentServiceReciever(new Handler());
        mReceiver.setReceiver(this);
        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, MyIntentService.class);

        /* Send optional extras to Download IntentService */
        intent.putExtra("url", serviceUrl);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("requestId", 101);

        startService(intent);
    }

    public void onTaskComplete(String result) {
        userArrayList = AlertWrapper.fromJson(result);
        Log.i("userArrayList", "userArrayList--> " + userArrayList);
        recyclerAdapter = new RecyclerAdapter2(ActivityIntentService.this, (ArrayList)userArrayList);
        recyclerView.setAdapter(recyclerAdapter);
        // do whatever you need

    }
    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case MyIntentService.STATUS_RUNNING:

                setProgressBarIndeterminateVisibility(true);
                break;
            case MyIntentService.STATUS_FINISHED:
                /* Hide progress & extract result from bundle */
                setProgressBarIndeterminateVisibility(false);

                String results = resultData.getString("result");

                /* Update ListView with result */
                onTaskComplete(results);

                break;
            case MyIntentService.STATUS_ERROR:
                /* Handle the error */
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        Log.v("YourActivity", "onDestory");
        super.onDestroy();
    }
}
