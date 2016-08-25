package com.example.sample.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
import android.view.ViewGroup;

import com.example.sample.R;
import com.example.sample.adapter.ComplexRecyclerAdapter;
import com.example.sample.adapter.RecyclerTouchListener;
import com.example.sample.data.User;

import java.util.ArrayList;

public class ComplexRecyclerViewSample extends AppCompatActivity implements RecyclerTouchListener.ClickListener {
    private RecyclerView recyclerView;
    private ComplexRecyclerAdapter complexRecyclerAdapter;
    private ArrayList<User> userArrayList;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_recycler_view_sample);
        Toolbar tool_bar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(tool_bar);
        getSupportActionBar().setElevation(26F);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.recycler_view_demo);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(ComplexRecyclerViewSample.this);
        recyclerView.setLayoutManager(mLayoutManager);
        userArrayList = new ArrayList<>();
        userArrayList.add(new User("manjula", 1, "software enginner"));
        userArrayList.add(new User("kishore", 2, "software enginner"));
        userArrayList.add(new User("murali", 3, "software enginner"));
        userArrayList.add(new User("naveen", 4, "software tester"));
        complexRecyclerAdapter = new ComplexRecyclerAdapter(ComplexRecyclerViewSample.this, userArrayList);
        recyclerView.setAdapter(complexRecyclerAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(ComplexRecyclerViewSample.this, recyclerView, this));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(getItemTouchHelper());
        itemTouchHelper.attachToRecyclerView(recyclerView);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Snackbar.make(recyclerView, "REFRESH", Snackbar.LENGTH_SHORT).show();
                complexRecyclerAdapter.notifyDataSetChanged();
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
                int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT) {
                    Snackbar.make(recyclerView, "LEFT swipe", Snackbar.LENGTH_SHORT).show();
                    complexRecyclerAdapter.removeItem(position);
                } else {
                    Snackbar.make(recyclerView, "RIGHT swipe", Snackbar.LENGTH_SHORT).show();
                    view = viewHolder.itemView;
                    removeView();
                    complexRecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Bitmap icon;
                Paint p = new Paint();
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            }
        };

        return itemTouchHelper;
    }
    private View view;
    private void removeView(){
        if(view.getParent()!=null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
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

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ComplexRecyclerViewSample.this);
        alertDialogBuilder.setTitle("Delete entry");
        alertDialogBuilder
                .setMessage("Are you sure you want to delete ? \n\nUser Name :  " + userArrayList.get(position).getName())
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        userArrayList.remove(position);
                        complexRecyclerAdapter.notifyDataSetChanged();
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