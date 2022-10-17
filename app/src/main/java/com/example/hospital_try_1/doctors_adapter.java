package com.example.hospital_try_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class doctors_adapter extends RecyclerView.Adapter<doctors_adapter.MyViewHolder>{

    Context c;
    int[] item_doctors_photo;
    String[] item_doctors_name;
    String[] item_specialist_in;
    String[] item_experience;
    String[] item_current_hospital;
    String[] item_timings;


    public doctors_adapter(Context ct,int[] doctors_photo,String[] doctors_name,String[] specialist_in,String[] experience,String[] current_hospital,String[] timings) {
        c=ct;
        item_doctors_photo=doctors_photo;
        item_doctors_name=doctors_name;
        item_specialist_in=specialist_in;
        item_experience=experience;
        item_current_hospital=current_hospital;
        item_timings=timings;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(c);
        View view=inflater.inflate(R.layout.doctors_adpater_layout,parent,false);
        return new doctors_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.iv_doctors_photo.setImageResource(item_doctors_photo[position]);
        holder.tv_doctors_name.setText(item_doctors_name[position]);
        holder.tv_experience.setText(item_experience[position]);
        holder.tv_current_hospital.setText(item_current_hospital[position]);
        holder.tv_timings.setText(item_timings[position]);


    }

    @Override
    public int getItemCount() {
        return item_doctors_name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_doctors_photo;

        TextView tv_doctors_name,tv_experience,tv_current_hospital,tv_timings;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_doctors_photo=itemView.findViewById(R.id.doctors_photo_roundedimag);
            tv_doctors_name=itemView.findViewById(R.id.doctors_name);
            tv_experience=itemView.findViewById(R.id.experience_tv);
            tv_current_hospital=itemView.findViewById(R.id.current_hospital_tv);
            tv_timings=itemView.findViewById(R.id.timings_tv);




        }
    }
}
