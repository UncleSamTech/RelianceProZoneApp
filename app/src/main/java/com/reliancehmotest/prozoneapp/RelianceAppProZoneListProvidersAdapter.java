package com.reliancehmotest.prozoneapp;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RelianceAppProZoneListProvidersAdapter extends RecyclerView.Adapter<RelianceAppProZoneListProvidersAdapter.RelianceAppProZoneViewHolder> {
    private ArrayList<RelianceProZoneAppModelClass> relianceProZoneAppModelClassArrayList;
    RelianceProZoneAppModelClass relianceProZoneAppModelClass;
    private View v;

    public RelianceAppProZoneListProvidersAdapter(ArrayList<RelianceProZoneAppModelClass> relianceProZoneAppModelClassArrayList) {
        this.relianceProZoneAppModelClassArrayList = relianceProZoneAppModelClassArrayList;
    }

    @NonNull
    @Override
    public RelianceAppProZoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reliance_provider_list_xml, parent, false);
        return new RelianceAppProZoneViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RelianceAppProZoneViewHolder holder, int position) {

        relianceProZoneAppModelClass = relianceProZoneAppModelClassArrayList.get(position);
        if(!TextUtils.isEmpty(relianceProZoneAppModelClass.getProv_loc())){
           holder.prov_locat.setText(relianceProZoneAppModelClass.getProv_loc());
        }
        else{
            holder.prov_locat.setText(RelianceAppProZoneConstants.EMPT_VAL);
        }

        if(!TextUtils.isEmpty(relianceProZoneAppModelClass.getProv_name())){
            holder.prov_name.setText(relianceProZoneAppModelClass.getProv_name());
        }

        else{
            holder.prov_name.setText(RelianceAppProZoneConstants.EMPT_VAL);
        }

        if (!TextUtils.isEmpty(relianceProZoneAppModelClass.getImg_prov())){
            Glide.with(v).load(relianceProZoneAppModelClass.getImg_prov()).into(holder.prov_img);
        }

        else{
            holder.prov_img.setImageResource(R.mipmap.reliance_loggo_foreground);
        }

    }

    @Override
    public int getItemCount() {
        return relianceProZoneAppModelClassArrayList.size();
    }

    class RelianceAppProZoneViewHolder  extends RecyclerView.ViewHolder{
        ImageView prov_img;
        TextView prov_name, prov_locat;

        public RelianceAppProZoneViewHolder(@NonNull View itemView) {
            super(itemView);
            prov_name = itemView.findViewById(R.id.prov_name);
            prov_locat = itemView.findViewById(R.id.prov_loc);
            prov_img = itemView.findViewById(R.id.provlist_loggo);
        }
    }
}
