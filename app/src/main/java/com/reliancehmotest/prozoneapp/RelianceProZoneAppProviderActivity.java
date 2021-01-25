package com.reliancehmotest.prozoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;

public class RelianceProZoneAppProviderActivity extends AppCompatActivity {
    private NetworkInfo networkInfo;
    private ConnectivityManager connMgr;
    private RecyclerView recyclerView;
    Context c = RelianceProZoneAppProviderActivity.this;
    private ArrayList<RelianceProZoneAppModelClass> relianceProZoneAppModelClassArrayList;
    private RelianceProZoneAppModelClass relianceProZoneAppModelClass;
    private RelianceAppProZoneListProvidersAdapter relianceAppProZoneListProvidersAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliance_pro_zone_app_provider);
        connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //the NetworkInfo class gets the current state of the device network connection
        networkInfo = connMgr.getActiveNetworkInfo();
        recyclerView = findViewById(R.id.rv_providers_list);
        relianceProZoneAppModelClassArrayList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(c,2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new RelItemDec(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(relianceAppProZoneListProvidersAdapter);
        recyclerView.addOnItemTouchListener(new RelProItemTouchListener(c, recyclerView, new RelProItemTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(c, RelianceProZoneProviderDetails.class));
            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));

        popProvList();





    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void popProvList(){
        relianceProZoneAppModelClassArrayList.add(new RelianceProZoneAppModelClass("","Chidi Umeh","Enugu Nigeria"));
        relianceProZoneAppModelClassArrayList.add(new RelianceProZoneAppModelClass("","Ekene Mark","Lagos Nigeria"));
        relianceProZoneAppModelClassArrayList.add(new RelianceProZoneAppModelClass("","Akpan Okon","Portharcourt Nigeria"));
        relianceProZoneAppModelClassArrayList.add(new RelianceProZoneAppModelClass("","Bisola Ayorkun","Ogun Nigeria"));
        relianceProZoneAppModelClassArrayList.add(new RelianceProZoneAppModelClass("","Yetunde Ivory","Ibadan Nigeria"));
        relianceProZoneAppModelClassArrayList.add(new RelianceProZoneAppModelClass("","Musa Hassan","Kano Nigeria"));
        relianceAppProZoneListProvidersAdapter.notifyDataSetChanged();
    }



}
