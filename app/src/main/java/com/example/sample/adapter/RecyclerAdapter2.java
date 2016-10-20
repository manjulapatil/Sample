package com.example.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sample.R;
import com.example.sample.data.Alert;

import java.util.ArrayList;

/**
 * Created by manjula on 9/22/16.
 */
public class RecyclerAdapter2  extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>{
    private Context context;
    private ArrayList<Alert> userList;

    public RecyclerAdapter2(Context activity, ArrayList<Alert> userList) {
        this.context = activity;
        this.userList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.temp.setText(userList.get(position).getMsg_value());
        holder1.label_textview.setText(userList.get(position).getMsg_key());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView temp;
        private TextView label_textview;
        public ViewHolder(View itemView) {
            super(itemView);
            label_textview = (TextView) itemView.findViewById(R.id.label_textview);
            temp = (TextView) itemView.findViewById(R.id.value_textview);
        }
    }
}
