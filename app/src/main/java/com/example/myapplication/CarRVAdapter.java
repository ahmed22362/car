package com.example.myapplication;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarRVAdapter extends RecyclerView.Adapter<CarRVAdapter.CarViewHolder> {
    ArrayList<Car> cars;
     OnRecyclerViewClickListener listener;
        View parent;

    public CarRVAdapter(ArrayList<Car> cars , OnRecyclerViewClickListener listener) {
        this.cars= cars;
        this.listener= listener;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_car_layout,null,false);
        CarViewHolder viewHolder = new CarViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car c = cars.get(position);

        if(c.getImg()!=null && !c.getImg().isEmpty()){
             holder.iv.setImageURI(Uri.parse(c.getImg()));}
        holder.tv_color.setText(c.getColor());
        holder.tv_model.setText(c.getModel());
        holder.tv_dpl.setText(String.valueOf(c.getDPL()));
        holder.id = c.getId();
        Log.e("error","position "+position);

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv_color , tv_model ,tv_dpl;
        int id;
        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.custom_car_img);
            tv_model = itemView.findViewById(R.id.custom_car_tv_model);
            tv_color = itemView.findViewById(R.id.custom_car_tv_color);
            tv_dpl = itemView.findViewById(R.id.custom_car_tv_dpl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id= (int) iv.getTag();
                    Log.e("error"," adapter");
                    listener.onItemClickListener(id);
                }
            });
        }
    }


}
