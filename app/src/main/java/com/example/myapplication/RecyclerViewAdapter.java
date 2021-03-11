//package com.example.myapplication;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CatViewHolder>{
//
//    ArrayList<Car> cars;
//
//    public RecyclerViewAdapter(ArrayList<Car> cats) {
//        this.cars = cars;
//    }
//
//    @NonNull
//    @Override
//    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout
//                .activity_car_layout,null,false);
//        CatViewHolder catHolder = new CatViewHolder(v);
//        return catHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
//        Cat c = cats.get(position);
//        holder.im_image.setImageResource(c.getImg());
//        holder.tv_name.setText(c.getName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return cats.size();
//    }
//
//
//    //ViewHolder for the adapter
//    class CatViewHolder extends RecyclerView.ViewHolder{
//        TextView tv_name;
//        ImageView im_image;
//
//        public CatViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tv_name = itemView.findViewById(R.id.cat_custom_tv);
//            im_image = itemView.findViewById(R.id.cat_custom_iv);
//        }
//    }
//
//}
