package com.reliancehmotest.prozoneapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RelianceProZoneAddNewProvider extends AppCompatActivity {
    private String onBoardStVal;
    private String provTypeVal;
    private Context c = RelianceProZoneAddNewProvider.this;
    private Uri imgUri;
    private NetworkInfo networkInfo;
    private ConnectivityManager connMgr;
    private String imgPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliance_pro_zone_add_new_provider);
        try {
            setOnBoardStatus();
            setProvType();
            getImg(R.id.img_prov_pics).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getLocalImgPath();
                }
            });

            getBut(R.id.btn_add_provid).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pushVal(getTInpEdt(R.id.edtProvName).getText().toString().trim(),getTInpEdt(R.id.edtProvDescr).getText().toString().trim(),Integer.parseInt(getTInpEdt(R.id.edtProfRating).getText().toString().trim()),getTInpEdt(R.id.edtProvAddr).getText().toString().trim(),retOnbStat(),retProvType(),getTInpEdt(R.id.edtProvState).getText().toString().trim());
                }
            });

            connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            //the NetworkInfo class gets the current state of the device network connection
            networkInfo = connMgr.getActiveNetworkInfo();


        } catch (Exception e) {
            Toast.makeText(c, "error as a result of " + e.getLocalizedMessage(), RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
        }
    }

    private TextInputEditText getTInpEdt(int id){
        return findViewById(id);
    }

    private ImageView getImg(int id){
        return findViewById(id);
    }

    private ImageButton getImgBut(int id){
        return findViewById(id);
    }





    private void setOnBoardStatus(){
        ArrayAdapter arrAd = getArrayAdapter(this, R.array.onb_stat_values, android.R.layout.simple_spinner_item, android.R.layout.simple_dropdown_item_1line);
        Spinner relSpin = getSpin(R.id.spin_onboard_stat);
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
        Spinner relSpin = getSpin(R.id.spin_onb_prov);
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


    public void getLocalImgPath() {
        try {
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, RelianceAppProZoneConstants.GALLERY_REQUEST_CODE);


        } catch (NullPointerException np) {
            Toast.makeText(c, "Error as a result of : " + np.getLocalizedMessage(), RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RelianceAppProZoneConstants.GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            try {
                imgUri = data.getData();
                imgPath = data.getDataString();
                Glide.with(c).load(imgUri).into(getImg(R.id.img_edt_user_prof));
            } catch (NullPointerException np) {
                Toast.makeText(c, "Error as a result of : " + np.getLocalizedMessage(), RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
            }
        }
    }



    public Uri getImgUri(){
        Uri uri = imgUri;
        return uri;}



private void pushVal(String name, String descript, int rating, String addr, String act_stat, String prov_type, String state){
    if (networkInfo != null && networkInfo.isConnectedOrConnecting() && networkInfo.isConnected()) {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(descript) && !TextUtils.isEmpty(addr) && !TextUtils.isEmpty(act_stat) && !TextUtils.isEmpty(prov_type) && !TextUtils.isEmpty(state)) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pro-zone.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RelProviderInterf proInt = retrofit.create(RelProviderInterf.class);
            ReliancePostProvidersModel relPostModel = new ReliancePostProvidersModel(name, descript, rating, addr, act_stat, prov_type, state);
            Call<ReliancePostProvidersModel> post_call = proInt.createPost(relPostModel);
            post_call.enqueue(new Callback<ReliancePostProvidersModel>() {
                @Override
                public void onResponse(Call<ReliancePostProvidersModel> call, Response<ReliancePostProvidersModel> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(c, " Error as a result of " + response.code(), RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
                    }

                    ReliancePostProvidersModel reliancePostProvidersModel = response.body();
                    String name = reliancePostProvidersModel.getName();
                    String descr = reliancePostProvidersModel.getDescription();
                    String status = reliancePostProvidersModel.getActive_status();
                    String addr = reliancePostProvidersModel.getAddress();
                    String id = reliancePostProvidersModel.getId();
                    int rating = reliancePostProvidersModel.getRating();
                    String prov_type = reliancePostProvidersModel.getProvider_type();
                    pushUserVal(name, "provid_pref_name", MODE_PRIVATE, "provid_pref_name_key");
                    pushUserVal(descr, "provid_pref_descr", MODE_PRIVATE, "provid_pref_descr_key");
                    pushUserVal(status, "provid_pref_status", MODE_PRIVATE, "provid_pref_status_key");
                    pushUserVal(id, "provid_pref_id", MODE_PRIVATE, "provid_pref_id_key");
                    pushUserVal(prov_type, "provid_pref_prov_type", MODE_PRIVATE, "provid_pref_prov_type_key");
                    pushUserVal(addr, "provid_pref_addr", MODE_PRIVATE, "provid_pref_descr_addr");
                    pushUserValInt(rating, "provid_pref_rating", MODE_PRIVATE, "provid_pref_rating_key");
                    startActivity(new Intent(c, RelianceProZoneAppProviderActivity.class));


                }

                @Override
                public void onFailure(Call<ReliancePostProvidersModel> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(c, " Empty values ", Toast.LENGTH_LONG).show();
        }
    }
}


    public void pushUserVal( String value,String key , int mode, String prefkey){
        SharedPreferences shPref = getSharedPreferences(prefkey,mode);
        SharedPreferences.Editor edt = shPref.edit();
        edt.putString(key,value);
        edt.apply();
    }

    public void pushUserValInt( int value,String key , int mode, String prefkey){
        SharedPreferences shPref = getSharedPreferences(prefkey,mode);
        SharedPreferences.Editor edt = shPref.edit();
        edt.putInt(key,value);
        edt.apply();
    }

    private Button getBut(int id){
        return findViewById(id);
    }

    private String retOnbStat(){
        return onBoardStVal;
    }

    private String retProvType(){
        return provTypeVal;
    }



}