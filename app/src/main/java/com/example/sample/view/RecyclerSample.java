package com.example.sample.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.sample.R;
import com.example.sample.adapter.RecyclerAdapter;
import com.example.sample.adapter.RecyclerTouchListener;
import com.example.sample.data.User;

import java.util.ArrayList;

public class RecyclerSample extends AppCompatActivity implements RecyclerTouchListener.ClickListener{
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<User> userArrayList;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_sample);
        Toolbar tool_bar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(tool_bar);
        getSupportActionBar().setElevation(26F);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.recycler_view_demo);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(RecyclerSample.this);
        recyclerView.setLayoutManager(mLayoutManager);
        userArrayList = new ArrayList<>();
        userArrayList.add(new User("manjula", 1, "software enginner"));
        userArrayList.add(new User("kishore", 2, "software enginner"));
        userArrayList.add(new User("murali", 3, "software enginner"));
        userArrayList.add(new User("naveen", 4, "software tester"));
        recyclerAdapter = new RecyclerAdapter(RecyclerSample.this, userArrayList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(RecyclerSample.this, recyclerView, this));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(getItemTouchHelper());
        itemTouchHelper.attachToRecyclerView(recyclerView);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Snackbar.make(recyclerView, "REFRESH", Snackbar.LENGTH_SHORT).show();
                recyclerAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    public ItemTouchHelper.SimpleCallback getItemTouchHelper() {

        ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                        Snackbar.make(recyclerView, "LEFT swipe", Snackbar.LENGTH_SHORT).show();
                } else {
                        Snackbar.make(recyclerView, "RIGHT swipe", Snackbar.LENGTH_SHORT).show();
                }
            }

        };

        return itemTouchHelper;
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, UserInfoDetailView.class);
        Log.e("userInfoList", "userInfoList : " + userArrayList.get(position));
        intent.putExtra("userObj", userArrayList.get(position));
        this.startActivity(intent);
    }

    @Override
    public void onLongClick(View view, int position) {
        showAlert(position);
    }

    private void showAlert(final int position) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RecyclerSample.this);
        alertDialogBuilder.setTitle("Delete entry");
        alertDialogBuilder
                .setMessage("Are you sure you want to delete ? \n\nUser Name :  " + userArrayList.get(position).getName())
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        userArrayList.remove(position);
                        recyclerAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                        Snackbar.make(recyclerView, "Data Deleted Successfully", Snackbar.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
