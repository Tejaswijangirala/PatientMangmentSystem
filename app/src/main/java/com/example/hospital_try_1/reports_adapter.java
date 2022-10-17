package com.example.hospital_try_1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class reports_adapter extends RecyclerView.Adapter<reports_adapter.ExampleViewHolder> {


    static Context c;
    ArrayList<String> loc_dateOfReport=new ArrayList<String>();
    ArrayList<String> loc_reportLink=new ArrayList<String>();

    public reports_adapter(Context ct,ArrayList<String> dateOfReport,ArrayList<String> reportLink) {
        c=ct;
        loc_dateOfReport=dateOfReport;
        loc_reportLink=reportLink;

    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reports_layout, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {

        holder.tv_rd.setText(loc_dateOfReport.get(position));

        holder.bt_viewreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent=new Intent(c,pdf_viewer_for_report.class);
                    intent.putExtra("pdf_link",loc_reportLink.get(position));
                    c.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return loc_dateOfReport.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rd;

        Button bt_viewreport;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            tv_rd=itemView.findViewById(R.id.report_tv);
            bt_viewreport=itemView.findViewById(R.id.viewreport_bt);
            ImageView roundedimag = (ImageView) itemView.findViewById(R.id.roundedimag);
            // Load an image using Picasso library
            Picasso.with(reports_adapter.c)
                    .load(R.drawable.report_image)
                    .into(roundedimag);
        }
    }
}

