package com.example.intentssqlite;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.widget.ImageView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.UserViewHolder> {

    //this context we will use to inflate the layout
    private Context context;

    //we are storing all the users in a list
    ArrayList<String> userInfo;

    public DataAdapter(ArrayList<String> userInfo) {

        this.userInfo = userInfo;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        //getting the user of the specified position
        holder.assignData(userInfo.get(position));


    }
    @Override
    public int getItemCount() {

        return userInfo.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView data;

        public UserViewHolder(View itemView) {
            super(itemView);
            data = (TextView) itemView.findViewById(R.id.data);
        }

        public void assignData(String datos){

            data.setText(datos);
        }
    }
}
