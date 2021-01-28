package com.reliancehmotest.prozoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    String images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliance_pro_zone_app_provider);
        try {
            String id = retrVal("provid_pref_id",MODE_PRIVATE,"provid_pref_id_key");
            String name = retrVal("provid_pref_name",MODE_PRIVATE,"provid_pref_name_key");
            String description = retrVal("provid_pref_descr",MODE_PRIVATE,"provid_pref_descr_key");
            int rating = retrValInt("provid_pref_rating",MODE_PRIVATE,"provid_pref_rating_key");
            String address = retrVal("provid_pref_addr",MODE_PRIVATE,"provid_pref_descr_addr");
            String active_status = retrVal("provid_pref_status",MODE_PRIVATE, "provid_pref_status_key");
            String provider_type = retrVal("provid_pref_prov_type",MODE_PRIVATE,"provid_pref_prov_type_key");
           // retrUpdData(name,description,active_status,id,provider_type,address,rating);
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
                    relianceProZoneAppModelClass = relianceProZoneAppModelClassArrayList.get(position);
                    pushUserVal(relianceProZoneAppModelClass.getName(),"prov_prof_pref_name",MODE_PRIVATE,"prov_prof_pref_name_key");
                    pushUserVal(relianceProZoneAppModelClass.getDescription(),"prov_prof_pref_descr",MODE_PRIVATE,"prov_prof_pref_descr_key");
                    pushUserVal(relianceProZoneAppModelClass.getActive_status(),"prov_prof_pref_status",MODE_PRIVATE,"prov_prof_pref_status_key");
                    pushUserVal(relianceProZoneAppModelClass.getAddress(),"prov_prof_pref_addr",MODE_PRIVATE,"prov_prof_pref_addr_key");
                    pushUserVal(relianceProZoneAppModelClass.getProvider_type(),"prov_prof_pref_type",MODE_PRIVATE,"prov_prof_pref_type_key");
                    pushUserVal(relianceProZoneAppModelClass.getImages(),"prov_prof_pref_img",MODE_PRIVATE,"prov_prof_pref_img_key");
                    pushUserVal(relianceProZoneAppModelClass.getState(),"prov_prof_pref_state",MODE_PRIVATE,"prov_prof_pref_state_key");

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


    public String retrVal(String key,  int mode,String prefKey){
        SharedPreferences sharedPreferences = getSharedPreferences(prefKey,mode);
        //if(sharedPreferences.contains("user_church")){}
        return sharedPreferences.getString(key,null);
    }

    public int retrValInt(String key,  int mode,String prefKey){
        SharedPreferences sharedPreferences = getSharedPreferences(prefKey,mode);
        return sharedPreferences.getInt(key,0);
    }

    private void pullAllProviders(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RelianceAppProZoneConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RelProviderInterf jsonApi = retrofit.create(RelProviderInterf.class);
        Call<ArrayList<RelianceProZoneAppModelClass>>  pro_call = jsonApi.getProviders();
        pro_call.enqueue(new Callback<ArrayList<RelianceProZoneAppModelClass>>() {
            @Override
            public void onResponse(Call<ArrayList<RelianceProZoneAppModelClass>> call, Response<ArrayList<RelianceProZoneAppModelClass>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(c, "response unsucessful  and response code : " + response.code(),Toast.LENGTH_LONG).show();
                    return;
                }

                ArrayList<RelianceProZoneAppModelClass> postActivities = response.body();
                for(RelianceProZoneAppModelClass postObjectClass : postActivities){

                        relianceProZoneAppModelClassArrayList.add(new RelianceProZoneAppModelClass("","","",0,"","","",""));
                        relianceAppProZoneListProvidersAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RelianceProZoneAppModelClass>> call, Throwable t) {
                Toast.makeText(c, "Error as a result of " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    private void pullAllProvidersByNameAddress(String name, String addr){
        if (networkInfo != null && networkInfo.isConnectedOrConnecting() && networkInfo.isConnected()) {
            Map<String, String> genSearch = new HashMap<>();
            genSearch.put("name", name);
            genSearch.put("address", addr);
            Retrofit retrofit = new Retrofit.Builder().baseUrl(RelianceAppProZoneConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RelProviderInterf jsonApi = retrofit.create(RelProviderInterf.class);
            Call<ArrayList<RelianceProZoneAppModelClass>> pro_call = jsonApi.getProvidersGenericSearch(genSearch);
            pro_call.enqueue(new Callback<ArrayList<RelianceProZoneAppModelClass>>() {
                @Override
                public void onResponse(Call<ArrayList<RelianceProZoneAppModelClass>> call, Response<ArrayList<RelianceProZoneAppModelClass>> response) {

                    if (!response.isSuccessful()) {
                        Toast.makeText(c, "response unsucessful  and response code : " + response.code(), Toast.LENGTH_LONG).show();
                        return;
                    }

                    ArrayList<RelianceProZoneAppModelClass> postActivities = response.body();
                    for (RelianceProZoneAppModelClass postObjectClass : postActivities) {

                        relianceProZoneAppModelClassArrayList.add(new RelianceProZoneAppModelClass("", "", "", 0, "", "", "", ""));
                        relianceAppProZoneListProvidersAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<RelianceProZoneAppModelClass>> call, Throwable t) {
                    Toast.makeText(c, "Error as a result of " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            Toast.makeText(c,"Oops! !..No Internet seen ", RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
        }

    }



    private void pullAllProvidersByTypeStatus(String type, String stat){
        if (networkInfo != null && networkInfo.isConnectedOrConnecting() && networkInfo.isConnected()) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RelianceAppProZoneConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RelProviderInterf jsonApi = retrofit.create(RelProviderInterf.class);
        Call<ArrayList<RelianceProZoneAppModelClass>>  pro_call = jsonApi.getProvidersByTypeStatus(type,stat);
        pro_call.enqueue(new Callback<ArrayList<RelianceProZoneAppModelClass>>() {
            @Override
            public void onResponse(Call<ArrayList<RelianceProZoneAppModelClass>> call, Response<ArrayList<RelianceProZoneAppModelClass>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(c, "response unsucessful  and response code : " + response.code(),Toast.LENGTH_LONG).show();
                    return;
                }

                ArrayList<RelianceProZoneAppModelClass> postActivities = response.body();
                for(RelianceProZoneAppModelClass postObjectClass : postActivities){

                    relianceProZoneAppModelClassArrayList.add(new RelianceProZoneAppModelClass("","","",0,"","","",""));
                    relianceAppProZoneListProvidersAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RelianceProZoneAppModelClass>> call, Throwable t) {
                Toast.makeText(c, "Error as a result of " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        }
        else{
            Toast.makeText(c,"Oops! !..No Internet seen ", RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
        }
    }

    private void retrUpdData(String name, String descr, String status, String id, String prov_type, String addr, int rating){
        relianceProZoneAppModelClassArrayList.add(new RelianceProZoneAppModelClass(id, name,  descr, rating, addr, status,  prov_type));
        relianceAppProZoneListProvidersAdapter.notifyDataSetChanged();
    }

    public void pushUserVal( String value,String key , int mode, String prefkey){
        SharedPreferences shPref = getSharedPreferences(prefkey,mode);
        SharedPreferences.Editor edt = shPref.edit();
        edt.putString(key,value);
        edt.apply();
    }




}
