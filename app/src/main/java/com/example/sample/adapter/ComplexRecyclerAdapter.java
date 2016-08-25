package com.example.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sample.R;
import com.example.sample.data.User;

import java.util.ArrayList;

/**
 * Created by manjula on 4/18/16.
 */
public class ComplexRecyclerAdapter extends RecyclerView.Adapter<ComplexRecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<User> userList;
    public static final int EVEN = 0;
    public static final int ODD = 1;

    public ComplexRecyclerAdapter(Context activity, ArrayList<User> userList) {
        this.context = activity;
        this.userList = userList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(viewType == EVEN){
            v = LayoutInflater.from(context).inflate(R.layout.complex_recycler_carview1, parent, false);
            return new EvenViewHolder(v);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.complex_recycler_cardview2, parent, false);
            return new OddViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder.getItemViewType() == EVEN){
            EvenViewHolder evenViewHolder = (EvenViewHolder) holder;
            evenViewHolder.score.setText(userList.get(position).getName());
        }else{
            OddViewHolder OddViewHolder = (OddViewHolder) holder;
            OddViewHolder.headline.setText(userList.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if((userList.get(position).getId()%2)== 0)
            return EVEN;
        else
            return ODD;
        //return super.getItemViewType(position);
    }
    public void addItem(User userObj) {
        userList.add(userObj);
        notifyItemInserted(userList.size());
    }

    public void removeItem(int position) {
        userList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, userList.size());
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    public class EvenViewHolder extends ViewHolder {
        TextView score;
        public EvenViewHolder(View itemView) {
            super(itemView);
            score = (TextView) itemView.findViewById(R.id.score);
        }
    }
    public class OddViewHolder extends ViewHolder {
        TextView headline;
        public OddViewHolder(View itemView) {
            super(itemView);
            headline = (TextView) itemView.findViewById(R.id.headline);
        }
    }
}
