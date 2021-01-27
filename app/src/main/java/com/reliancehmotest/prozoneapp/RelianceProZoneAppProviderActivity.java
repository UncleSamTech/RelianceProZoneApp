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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RelianceProZoneAppProviderActivity extends AppCompatActivity {
    private NetworkInfo networkInfo;
    private ConnectivityManager connMgr;
    private RecyclerView recyclerView;
    Context c = RelianceProZoneAppProviderActivity.this;
    private ArrayList<RelianceProZoneAppModelClass> relianceProZoneAppModelClassArrayList;
    private RelianceProZoneAppModelClass relianceProZoneAppModelClass;
    private RelianceAppProZoneListProvidersAdapter relianceAppProZoneListProvidersAdapter;
    private String selCrit;
    private String onBoardStVal;
    private String provTypeVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliance_pro_zone_app_provider);
        try {
            connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            //the NetworkInfo class gets the current state of the device network connection
            networkInfo = connMgr.getActiveNetworkInfo();
            recyclerView = findViewById(R.id.rv_providers_list);
            relianceProZoneAppModelClassArrayList = new ArrayList<>();
            relianceAppProZoneListProvidersAdapter = new RelianceAppProZoneListProvidersAdapter(relianceProZoneAppModelClassArrayList);
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

            getFab(R.id.fab_add_provider).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(c, RelianceProZoneAddNewProvider.class));
                }
            });

            popSearchCriteria();

            popProvList();
        } catch (Exception e) {
            Toast.makeText(c, " Error as a result of " + e.getLocalizedMessage(),RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
        }


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

    private FloatingActionButton getFab(int id){
        return findViewById(id);
    }

    private Spinner getSpin(int id){
        return findViewById(id);
    }

    /**
     * This method is used for returning arrayAdapter
     *
     * @param arr_cont_id
     * @param lay_id
     * @param dropId
     * @return
     */
    private ArrayAdapter getArrayAdapter(Context c, int arr_cont_id, int lay_id, int dropId) {
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(c, arr_cont_id, lay_id);
        arrayAdapter.setDropDownViewResource(dropId);
        return arrayAdapter;
    }




    private void popSearchCriteria(){
        ArrayAdapter arrAd = getArrayAdapter(this, R.array.search_provid_type, android.R.layout.simple_spinner_item, android.R.layout.simple_dropdown_item_1line);
        Spinner relSpin = getSpin(R.id.spin_filter_options);
        relSpin.setAdapter(arrAd);
        relSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selCrit = (String) parent.getItemAtPosition(position);
                    if(selCrit.equals("Search By Provider Type")){
                        setProvType();
                    }
                    else if(selCrit.equals("Search By OnBoarding Status")){
                        setOnBoardStatus();
                    }

                    else{
                        Toast.makeText(c,"No valid selection made", RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String retFilterType(){
        return selCrit;
    }




    private void setOnBoardStatus(){
        ArrayAdapter arrAd = getArrayAdapter(this, R.array.onb_stat_values, android.R.layout.simple_spinner_item, android.R.layout.simple_dropdown_item_1line);
        Spinner relSpin = getSpin(R.id.spin_filter_options_values);
        relSpin.setAdapter(arrAd);
        relSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onBoardStVal = (String) parent.getItemAtPosition(position);
                if(onBoardStVal.equals("---Select Registration Status---")){
                    Toast.makeText(c, " Make a selection ", RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setProvType(){
        ArrayAdapter arrAd = getArrayAdapter(this, R.array.provid_type_val, android.R.layout.simple_spinner_item, android.R.layout.simple_dropdown_item_1line);
        Spinner relSpin = getSpin(R.id.spin_filter_options_values);
        relSpin.setAdapter(arrAd);
        relSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provTypeVal = (String) parent.getItemAtPosition(position);
                if(provTypeVal.equals("---Select Provider Type---")){
                    Toast.makeText(c, " Make a selection ", RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }





}
