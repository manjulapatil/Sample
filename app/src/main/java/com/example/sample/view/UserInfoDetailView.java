package com.example.sample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sample.R;
import com.example.sample.adapter.RecyclerAdapter2;
import com.example.sample.data.Alert;
import com.example.sample.data.AlertWrapper;
import com.example.sample.interfaces.UserInfoResponseCallback;
import com.example.sample.services.UserInfoAsynchronousTask;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDetailView extends AppCompatActivity implements UserInfoResponseCallback<String> {
    private String serviceUrl = "http://192.168.0.62/leadme/serviceleadme/getAlertMessages";
    private RecyclerView recyclerView;
    private RecyclerAdapter2 recyclerAdapter;
    private List<Alert> userArrayList= new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;

    public void onTaskComplete(String result) {
        Log.i("result", "result--> " + result);
        userArrayList = AlertWrapper.fromJson(result);
        Log.i("userArrayList", "userArrayList--> " + userArrayList);
        recyclerAdapter = new RecyclerAdapter2(UserInfoDetailView.this, (ArrayList)userArrayList);
        recyclerView.setAdapter(recyclerAdapter);
        // do whatever you need
    /*    userArrayList = new ArrayList<>();
        userArrayList.add(new User("manjula", 1, "software enginner"));
        userArrayList.add(new User("kishore", 2, "software enginner"));
        userArrayList.add(new User("murali", 3, "software enginner"));
        userArrayList.add(new User("naveen", 4, "software tester"));*/
    }

    public void launchTask(String url) {
        UserInfoAsynchronousTask a = new UserInfoAsynchronousTask(UserInfoDetailView.this, this);
        a.execute(url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_detail_view);
        Button button = (Button) findViewById(R.id.execute_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(UserInfoDetailView.this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerAdapter = new RecyclerAdapter2(UserInfoDetailView.this, (ArrayList)userArrayList);
        recyclerView.setAdapter(recyclerAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTask(serviceUrl);
            }
        });
    }
}
