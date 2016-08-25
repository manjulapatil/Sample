package com.example.sample.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.sample.R;
import com.example.sample.adapter.RecyclerAdapter;
import com.example.sample.adapter.RecyclerTouchListener;
import com.example.sample.data.User;

import java.util.ArrayList;

public class BottomSheetDialogActivity extends AppCompatActivity implements RecyclerTouchListener.ClickListener{
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<User> userArrayList;
    private RecyclerView.LayoutManager mLayoutManager;
    BottomSheetBehavior behavior;
    private BottomSheetDialog mBottomSheetDialog;
    private Button btnView, btnDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog);

        btnView = (Button) findViewById(R.id.btnView);
        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
              /*  if(behavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else{*/
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
              //  }
            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
        // The View with the BottomSheetBehavior
        View bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setPeekHeight(256);
        behavior.setHideable(false);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // React to state change
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(BottomSheetDialogActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        userArrayList = new ArrayList<>();
        userArrayList.add(new User("manjula", 1, "software enginner"));
        userArrayList.add(new User("kishore", 2, "software enginner"));
        userArrayList.add(new User("murali", 3, "software enginner"));
        userArrayList.add(new User("naveen", 4, "software tester"));
        recyclerAdapter = new RecyclerAdapter(BottomSheetDialogActivity.this, userArrayList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(BottomSheetDialogActivity.this, recyclerView, this));
    }
    private void showBottomSheetDialog() {
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        mBottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.sheet, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(BottomSheetDialogActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        userArrayList = new ArrayList<>();
        userArrayList.add(new User("manjula", 1, "software enginner"));
        userArrayList.add(new User("kishore", 2, "software enginner"));
        userArrayList.add(new User("murali", 3, "software enginner"));
        userArrayList.add(new User("naveen", 4, "software tester"));
        recyclerAdapter = new RecyclerAdapter(BottomSheetDialogActivity.this, userArrayList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(BottomSheetDialogActivity.this, recyclerView, this));

        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
    }
    @Override
    public void onClick(View view, int position) {
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
    }

    @Override
    public void onLongClick(View view, int position) {

    }
    @Override
    public void onBackPressed() {
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}

